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
 * @since 20 de abr de 2019 01:19:47
 * @version 1.0
 */
@Data
@AllArgsConstructor
@ConfigurationProperties("seguranca")
public class SegurancaConfBeans implements Serializable {

	private String chave;
}
