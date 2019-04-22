/**
 *
 */
package com.br.alldreams.socialmidia.manager.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 21:11:34
 * @version 1.0
 */
@Data
@Entity
@Table(name = "tbl_configuration")
public class Configuracao implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -504749554848435808L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private Usuario usuario;

	@Column(name = "description")
	private String descricao;

	@Column(name = "start")
	private String horaFim;

	@Column(name = "stop")
	private String horaInicio;

	@ManyToMany
	@JoinTable(name = "tbl_configs_tags", inverseJoinColumns = @JoinColumn(name = "id_tag"), joinColumns = @JoinColumn(name = "id_config"))
	private List<Tag> tags;

	@ManyToMany
	@JoinTable(name = "tbl_configs_comments", inverseJoinColumns = @JoinColumn(name = "id_comment"), joinColumns = @JoinColumn(name = "id_config"))
	private List<Comentario> comentarios;

	@Column(name = "executed")
	private Date ultimaExecucao;

	@Enumerated(EnumType.STRING)
	@Column(name = "browser")
	private BrowserEnum navegador;

	@Column(name = "likePercent")
	private Integer percentualCurtidas;

	@Column(name = "sharePercent")
	private Integer percentualCompartilhada;
}
