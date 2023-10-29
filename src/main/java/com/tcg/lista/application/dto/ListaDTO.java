package com.tcg.lista.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ListaDTO(
        Long id,
        Long usuarioId,
        String nome,
        LocalDateTime dataCriacao,
        LocalDateTime dataValidade,
        LocalDateTime dataConclusao,
        boolean isConcluida,
        List<ItemDTO> itens
        ) {
}
