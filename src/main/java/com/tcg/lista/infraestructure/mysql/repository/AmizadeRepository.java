package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.usuario.Amizade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {
}
