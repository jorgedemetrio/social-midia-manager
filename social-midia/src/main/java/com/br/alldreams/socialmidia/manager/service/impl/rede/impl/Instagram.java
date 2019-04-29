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

	private Random rnd = new Random();

	public Instagram(@NonNull Configuracao configuracao, @NonNull JobServiceImpl jobService, @NonNull WebDriver driver) {
		super(configuracao, jobService, driver);
	}

	private void curtir(List<WebElement> lista) {
		WebElement coracao = null;
		for (WebElement webElement : lista) {

			try {
				coracao = getParent(webElement);
				if (coracao != null && "button".equalsIgnoreCase(coracao.getTagName())) {
					coracao.click();
				}
			} catch (Exception ex) {
				log.error(ex.getMessage(), ex);
			}
			esperar(1, 4);
		}
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

		while (lista != null && lista.size() > 1) {
			curtir(lista);
			contar();
			lista = getDriver().findElements(By.cssSelector("button span.glyphsSpriteHeart__outline__24__grey_9[aria-label='Curtir']"));
		}

		return Boolean.TRUE;
	}

	@Override
	public Boolean curtirTag() {

		List<Tag> tags = getConfiguracao().getTags();

		rorder(tags);

//		Collections.sort(tags, new Comparator<Tag>() {
//			Random rnd = new Random();
//
//			@Override
//			public int compare(Tag o1, Tag o2) {
//				return rnd.nextInt();
//			}
//
//		});
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
//				try {
//					if (Boolean.TRUE) {// rnd.nextInt(5) <= 2 && !getConfiguracao().getComentarios().isEmpty()) {
//						String valor = Comentario.class.cast(rorder(getConfiguracao().getComentarios()).get(0)).getDescricao();
//
//						esperar();
//						getJs().executeScript("setTimeout(function(){$$('.Ypffh')[0].value=\"" + valor + "\";},500);");
//
//						esperar(2);
//						getDriver().findElement(By.tagName("button").className("_0mzm-")).click();
//						esperar(1);
//					}
//				} catch (Exception ex) {
//					log.error(ex.getMessage(), ex);
//				}

				try {
					getDriver().findElement(By.tagName("button").className("ckWGn")).click();
				} catch (Exception ex) {
					log.error(ex.getMessage(), ex);
				}
				contar();

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

	@SuppressWarnings("unchecked")
	private List rorder(List listas) {
		Collections.sort(listas, new Comparator<Object>() {
			Random rnd = new Random();

			@Override
			public int compare(Object o1, Object o2) {
				return rnd.nextInt();
			}

		});
		return listas;
	}

}
