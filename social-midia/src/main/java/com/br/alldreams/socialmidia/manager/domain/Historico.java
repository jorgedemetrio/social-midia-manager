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
 * @since 19 de abr de 2019 19:07:25
 * @version 1.0
 */
@Data
@Entity
@Table(name = "tbl_history")
public class Historico implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3109478016490767248L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "url")
	private String url;

	@Column(name = "comment")
	private Comentario comentario;

	@Column(name = "tag")
	private Tag tag;

}
