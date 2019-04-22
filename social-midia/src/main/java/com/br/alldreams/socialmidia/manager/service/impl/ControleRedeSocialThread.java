/**
 *
 */
package com.br.alldreams.socialmidia.manager.service.impl;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.br.alldreams.socialmidia.manager.domain.BrowserEnum;
import com.br.alldreams.socialmidia.manager.domain.Configuracao;
import com.br.alldreams.socialmidia.manager.domain.TipoRedeEnum;
import com.br.alldreams.socialmidia.manager.service.impl.rede.RedeSocialAcoes;
import com.br.alldreams.socialmidia.manager.service.impl.rede.impl.Instagram;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 22:40:01
 * @version 1.0
 */
@Slf4j
@Data
@RequiredArgsConstructor
public class ControleRedeSocialThread implements Runnable {

	@NonNull
	private Configuracao configuracao;

	@NonNull
	private JobServiceImpl jobService;

	private boolean controleExecucao = false;

	private WebDriver driver;

	private RedeSocialAcoes rede;

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		controleExecucao = false;
	}

	private void getNewDriver() {
		if (BrowserEnum.FIREFOX.equals(getConfiguracao().getNavegador())) {
			System.setProperty("webdriver.gecko.driver", jobService.getDrivesConfig().getFirefox());
			driver = new FirefoxDriver();
		} else if (BrowserEnum.CHROME.equals(getConfiguracao().getNavegador())) {

			System.setProperty("webdriver.chrome.driver", jobService.getDrivesConfig().getChrome());
			driver = new ChromeDriver();
		} else if (BrowserEnum.IE.equals(getConfiguracao().getNavegador())) {
			driver = new InternetExplorerDriver();
		} else
			driver = null;

		if (driver != null) {
			if (TipoRedeEnum.INSTAGRAM.equals(getConfiguracao().getUsuario().getTipo())) {
				rede = new Instagram(configuracao, jobService, driver);
			}

			// Maximize window
			driver.manage().window().maximize();

			// Set the Script Timeout to 20 seconds
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		}
	}

	@Override
	public void run() {
		this.controleExecucao = true;
		getNewDriver();
		rede.esperar();
		while (controleExecucao) {
			rede.entrar();
			if (getConfiguracao().getPercentualCurtidas() >= 1) {
				rede.curtirHome();
			}
			if (getConfiguracao().getTags().size() >= 1) {
				rede.curtirTag();
			}
			rede.esperar();
		}
		driver.close();
		driver = null;
		jobService.removeEvento(configuracao.getDescricao());
	}
}
