package com.br.alldreams.socialmidia.conf.beans;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ConfigurationProperties("drivers")
public class DriversConfBeans implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2010557364887526513L;
	private String chrome;
	private String firefox;
	private String ie;
	private String opera;
}
