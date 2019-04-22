/**
 *
 */
package com.br.alldreams.socialmidia.manager.service.impl.rede;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.br.alldreams.socialmidia.manager.domain.Configuracao;
import com.br.alldreams.socialmidia.manager.service.impl.JobServiceImpl;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jorge Demetrio
 * @since 21 de abr de 2019 21:15:59
 * @version 1.0
 */
@Slf4j
@Data
@RequiredArgsConstructor
public abstract class RedeSocialAcoes {
	public static final int UM_SEGUNDO = 1000;

	public static final int MAX_SCROLLS = 30;

	public static void esperar() {
		try {
			Thread.currentThread().sleep(UM_SEGUNDO);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void esperar(final int tempo) {
		try {
			Thread.currentThread().sleep(UM_SEGUNDO * tempo);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}

	@NonNull
	private Configuracao configuracao;

	@NonNull
	private JobServiceImpl jobService;

	@NonNull
	private WebDriver driver;

	private JavascriptExecutor js = null;

	public abstract Boolean curtirHome();

	public abstract Boolean curtirTag();

	public void descer() {
		getJs().executeAsyncScript("window.scrollTo(0,document.body.scrollHeight);");

	}

	public abstract Boolean entrar();

	public JavascriptExecutor getJs() {
		if (js == null) {
			js = (JavascriptExecutor) getDriver();
		}
		return js;
	}
}
