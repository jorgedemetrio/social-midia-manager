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
 * @since 19 de abr de 2019 19:06:59
 * @version 1.0
 */
@Data
@Entity
@Table(name = "tbl_comment")
public class Comentario implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1461428966392639610L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "description", nullable = true, unique = true, insertable = true, updatable = false, length = 255)
	private String descricao;

}
