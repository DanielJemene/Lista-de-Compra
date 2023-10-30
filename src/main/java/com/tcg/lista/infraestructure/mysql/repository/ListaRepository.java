package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.entity.lista.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ListaRepository extends JpaRepository<Lista, Long> {

    Optional<List<Lista>> findListaByUsuarioId(@Param("usuario_id") Long id);
}

