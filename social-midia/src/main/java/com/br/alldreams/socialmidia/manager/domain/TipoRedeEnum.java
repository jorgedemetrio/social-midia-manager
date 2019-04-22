/**
 *
 */
package com.br.alldreams.socialmidia.manager.domain;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 19:02:07
 * @version 1.0
 */
public enum TipoRedeEnum {
	FACEBOOK(""), TWITTER(""), INSTAGRAM("https://www.instagram.com/accounts/login/?source=auth_switcher");

	private String url;

	private TipoRedeEnum(final String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
