package com.tcg.lista.domain.lista;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListaRepository extends JpaRepository<Lista, Long> {
    Optional<Lista> findByNome(String nome);
}

