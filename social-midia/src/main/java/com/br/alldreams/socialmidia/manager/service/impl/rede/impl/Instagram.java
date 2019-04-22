/**
 *
 */
package com.br.alldreams.socialmidia.manager.service.impl.rede.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.br.alldreams.socialmidia.manager.domain.Configuracao;
import com.br.alldreams.socialmidia.manager.domain.Tag;
import com.br.alldreams.socialmidia.manager.domain.TipoRedeEnum;
import com.br.alldreams.socialmidia.manager.domain.Usuario;
import com.br.alldreams.socialmidia.manager.service.impl.JobServiceImpl;
import com.br.alldreams.socialmidia.manager.service.impl.rede.RedeSocialAcoes;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jorge Demetrio
 * @since 21 de abr de 2019 21:17:01
 * @version 1.0
 */
@Slf4j
public class Instagram extends RedeSocialAcoes {

	public Instagram(@NonNull Configuracao configuracao, @NonNull JobServiceImpl jobService, @NonNull WebDriver driver) {
		super(configuracao, jobService, driver);
	}

	@Override
	public Boolean curtirHome() {

		getDriver().get("https://www.instagram.com/");

		for (int i = 0; i < MAX_SCROLLS; i++) {
			try {
				descer();
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
			esperar(1, 3);
		}

		topo();
		esperar(30);

		List<WebElement> lista = getDriver().findElements(By.cssSelector("button span.glyphsSpriteHeart__outline__24__grey_9[aria-label='Curtir']"));
		// By.className("glyphsSpriteHeart__outline__24__grey_9"));
		WebElement coracao = null;
		for (WebElement webElement : lista) {

			// while (lista != null && lista.size() > 1) {
//			WebElement webElement = lista.get(0);
			try {
				coracao = getParent(webElement);
				if (coracao != null && "button".equalsIgnoreCase(coracao.getTagName())) {
					coracao.click();
				}
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
			// lista =
			// getDriver().findElements(By.className("glyphsSpriteHeart__outline__24__grey_9"));
			esperar(1, 4);
		}

		return Boolean.TRUE;
	}

	@Override
	public Boolean curtirTag() {

		List<Tag> tags = getConfiguracao().getTags();

		Collections.sort(tags, new Comparator<Tag>() {
			Random rnd = new Random();

			@Override
			public int compare(Tag o1, Tag o2) {
				return rnd.nextInt();
			}

		});
		for (Tag tag : tags) {
			getDriver().get("https://www.instagram.com/explore/tags/" + tag.getDescricao());

			for (int i = 0; i < MAX_SCROLLS; i++) {
				descer();
				esperar(1, 3);
			}
			topo();
			esperar(30);
			List<WebElement> coracaos = null;
			List<WebElement> lista = getDriver().findElements(By.className("v1Nh3"));
			for (WebElement webElement : lista) {
				webElement.click();
				esperar(2);
				try {
					coracaos = getDriver().findElements(By.className("glyphsSpriteHeart__outline__24__grey_9"));
					if (coracaos != null) {
						for (WebElement coracao : coracaos) {
							WebElement bt = getParent(coracao);
							if (bt != null && "button".equalsIgnoreCase(bt.getTagName())) {
								getParent(coracao).click();
								break;
							}
						}

					}
				} catch (Exception ex) {
					log.error(ex.getMessage(), ex);
				}
				esperar(2);
				try {
					getDriver().findElement(By.tagName("button").className("ckWGn")).click();
				} catch (Exception ex) {
					log.error(ex.getMessage(), ex);
				}

			}

		}

		return Boolean.TRUE;
	}

	@Override
	public Boolean entrar() {

		Usuario usuario = getConfiguracao().getUsuario();

		getDriver().get(TipoRedeEnum.INSTAGRAM.getUrl());
		esperar(5);
		getDriver().findElement(By.name("username")).sendKeys(usuario.getUsuario());
		esperar(2);
		getDriver().findElement(By.name("password")).sendKeys(getJobService().getCriptografia().decriptografia(usuario.getSenha()));
		for (WebElement element : getDriver().findElements(By.tagName("button"))) {
			if ("submit".equalsIgnoreCase(element.getAttribute("type"))) {
				element.click();
				break;
			}
		}
		esperar(10);
		for (WebElement element : getDriver().findElements(By.tagName("button").className("HoLwm"))) {
			element.click();
			break;
		}

		return Boolean.TRUE;
	}

}
