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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	@JoinColumn(name = "id_user", nullable = false, unique = false, insertable = true, updatable = false)
	private Usuario usuario;

	@Column(name = "description", nullable = false, unique = true, insertable = true, updatable = true, length = 45)
	private String descricao;

	@Column(name = "start", nullable = false, unique = false, insertable = true, updatable = true, length = 4)
	private String horaFim;

	@Column(name = "stop", nullable = false, unique = false, insertable = true, updatable = true, length = 4)
	private String horaInicio;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "tbl_configs_tags", inverseJoinColumns = @JoinColumn(name = "id_tag"), joinColumns = @JoinColumn(name = "id_config"))
	private List<Tag> tags;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "tbl_configs_comments", inverseJoinColumns = @JoinColumn(name = "id_comment"), joinColumns = @JoinColumn(name = "id_config"))
	private List<Comentario> comentarios;

	@Column(name = "executed", nullable = true, unique = false, insertable = true, updatable = true)
	private Date ultimaExecucao;

	@Enumerated(EnumType.STRING)
	@Column(name = "browser", nullable = false, unique = false, insertable = true, updatable = true, length = 10)
	private BrowserEnum navegador;

	@Column(name = "likePercent", nullable = true, unique = false, insertable = true, updatable = true, columnDefinition = "INT(3) DEFAULT 0")
	private Integer percentualCurtidas;

	@Column(name = "sharePercent", nullable = true, unique = false, insertable = true, updatable = true, columnDefinition = "INT(3) DEFAULT 0")
	private Integer percentualCompartilhada;
}
