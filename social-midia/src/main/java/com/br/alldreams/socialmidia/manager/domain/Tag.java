/**
 *
 */
package com.br.alldreams.socialmidia.manager.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 19:07:09
 * @version 1.0
 */
@Data

@Entity
@Table(name = "tbl_tag")
public class Tag implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2315832731700227573L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "description")
	private String descricao;

}
