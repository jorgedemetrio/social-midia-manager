/**
 *
 */
package com.br.alldreams.socialmidia.manager.service.impl.rede;

import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

	public static final int MAX_SCROLLS = 20;
	private final static Random rnd = new Random();

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

	public static void esperar(final int inicio, final int fim) {
		try {

			Thread.currentThread().sleep(inicio + (UM_SEGUNDO * rnd.nextInt(fim - inicio)));
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
		try {
			esperar();
			getJs().executeScript("setTimeout(function(){ window.scrollTo(0,document.body.scrollHeight);  }, 500);");
			esperar();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}

	}

	public abstract Boolean entrar();

	public JavascriptExecutor getJs() {
		if (js == null) {
			js = (JavascriptExecutor) getDriver();
		}
		return js;
	}

	public WebElement getParent(final WebElement webElement) {
		try {
			return (WebElement) getJs().executeScript("return arguments[0].parentElement;", webElement);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return null;
	}

	public void topo() {
		try {
			esperar();
			getJs().executeScript("document.sobe = function (){\r\n" + 
					"	if(window.scrollY > 5) { \r\n" + 
					"		if(window.scrollY - 100 > 5){\r\n" + 
					"			window.scrollTo(0, window.scrollY - 220);\r\n" + 
					"			setTimeout(function(){document.sobe();}, 10);\r\n" + 
					"		}\r\n" + 
					"		else{\r\n" + 
					"			window.scrollTo(0, 0);\r\n" + 
					"		}\r\n" + 
					"	}\r\n" + 
					"}\r\n" + 
					"document.sobe();");
			esperar();
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}

	}
}
