/**
 *
 */
package com.br.alldreams.socialmidia.manager.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 19:01:28
 * @version 1.0
 */
@Data
@Table(name = "tbl_user")
@Entity
public class Usuario implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8490405829215899109L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String nome;

	@Column(name = "user")
	private String usuario;

	@Column(name = "pass")
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TipoRedeEnum tipo;

	@OneToMany(mappedBy = "usuario")
	private List<Configuracao> configuracoes;
}
