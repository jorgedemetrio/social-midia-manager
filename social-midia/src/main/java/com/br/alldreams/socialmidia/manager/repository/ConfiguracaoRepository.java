package com.br.alldreams.socialmidia.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.br.alldreams.socialmidia.manager.domain.Configuracao;

@Repository
public interface ConfiguracaoRepository extends JpaRepository<Configuracao, Long> {

	@Query(name = "getConfiguracoesAtivas", value = "FROM Configuracao c WHERE c.horaInicio >= :hora AND c.horaFim <= :hora AND c.habilitado = true ")
	public List<Configuracao> getConfiguracoesAtivas(final String hora);

}
