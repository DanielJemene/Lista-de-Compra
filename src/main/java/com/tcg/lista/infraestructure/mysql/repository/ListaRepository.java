package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.lista.Lista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListaRepository extends JpaRepository<Lista, Long> {
    Optional<Lista> findByNome(String nome);
}

