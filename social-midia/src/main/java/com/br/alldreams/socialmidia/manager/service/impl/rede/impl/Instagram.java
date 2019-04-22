/**
 *
 */
package com.br.alldreams.socialmidia.manager.service.impl.rede.impl;

import java.util.List;

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
			descer();
			esperar(5);
		}

		List<WebElement> lista = getDriver().findElements(By.className("glyphsSpriteHeart__outline__24__grey_9"));
		for (WebElement webElement : lista) {
			webElement.findElement(By.xpath("./..")).click();
			esperar(2);
		}

		return Boolean.TRUE;
	}

	@Override
	public Boolean curtirTag() {

		List<Tag> tags = getConfiguracao().getTags();

		for (Tag tag : tags) {
			getDriver().get("https://www.instagram.com/explore/tags/" + tag.getDescricao());

			for (int i = 0; i < MAX_SCROLLS; i++) {
				descer();
				esperar(5);
			}
			WebElement coracao = null;
			WebElement fechar = null;
			List<WebElement> lista = getDriver().findElements(By.className("v1Nh3"));
			for (WebElement webElement : lista) {
				webElement.findElement(By.xpath("./..")).click();
				esperar(2);
				coracao = getDriver().findElement(By.className("glyphsSpriteHeart__outline__24__grey_9"));
				if (coracao != null) {
					coracao.findElement(By.xpath("./..")).click();
				}
				esperar(2);
				fechar = getDriver().findElement(By.className("ckWGn").tagName("button"));
				if (fechar != null)
					fechar.click();

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
		for (WebElement element : getDriver().findElements(By.tagName("button").className("HoLwm").className("aOOlW"))) {
			element.click();
			break;
		}

		return Boolean.TRUE;
	}

}
