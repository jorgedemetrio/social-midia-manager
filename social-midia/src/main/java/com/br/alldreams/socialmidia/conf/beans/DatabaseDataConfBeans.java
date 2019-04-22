/**
 *
 */
package com.br.alldreams.socialmidia.conf.beans;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 18:04:03
 * @version 1.0
 */
@Data
@AllArgsConstructor
@ConfigurationProperties("banco")
public class DatabaseDataConfBeans implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4162732197728353133L;
	private String host;
	private String database;
	private String user;
	private String pass;

}
