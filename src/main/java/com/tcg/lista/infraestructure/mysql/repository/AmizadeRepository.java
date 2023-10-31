package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.entity.amizade.Amizade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {

    Optional<List<Amizade>> findAmizadeByUsuarioId(@Param("usuario_id") Long usuarioId);
}
