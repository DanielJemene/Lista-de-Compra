package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.entity.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<List<Autor>> findAutorByListaId(@Param("lista_id") Long id);
}
