package com.tcg.lista.application.dto;

import com.tcg.lista.domain.entity.autor.AutorStatus;

public record AutorDTO(
        Long id,
        Long usuarioId,
        String nome,
        Long listaId,
        AutorStatus status
) {
}
