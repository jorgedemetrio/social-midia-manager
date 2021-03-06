/**
 *
 */
package com.br.alldreams.socialmidia.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.alldreams.socialmidia.manager.domain.Comentario;

/**
 * @author Jorge Demetrio
 * @since 19 de abr de 2019 20:46:12
 * @version 1.0
 */
@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
