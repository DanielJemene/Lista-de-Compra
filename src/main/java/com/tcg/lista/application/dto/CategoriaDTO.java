package com.tcg.lista.application.dto;

import com.tcg.lista.domain.enitty.categoria.CategoriaStatus;

public record CategoriaDTO(
        Long id,
        Long usuarioId,
        String nome,
        CategoriaStatus status) {
}
