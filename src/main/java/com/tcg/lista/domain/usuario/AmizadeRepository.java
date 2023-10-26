package com.tcg.lista.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmizadeRepository extends JpaRepository<Amizade, UUID> {
}
