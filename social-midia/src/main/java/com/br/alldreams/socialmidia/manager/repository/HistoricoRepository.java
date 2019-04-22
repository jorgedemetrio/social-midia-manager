/**
 *
 */
package com.br.alldreams.socialmidia.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.alldreams.socialmidia.manager.domain.Historico;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 20:50:16
 * @version 1.0
 */
@Repository
public interface HistoricoRepository extends JpaRepository<Historico, Long> {

}
