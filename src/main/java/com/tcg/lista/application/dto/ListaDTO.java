package com.tcg.lista.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ListaDTO(
        Long id,
        Long usuarioId,
        String nome,
        BigDecimal precoTotal,
        LocalDateTime dataCriacao,
        LocalDateTime dataValidade,
        LocalDateTime dataConclusao,
        boolean isConcluida,
        List<ItemDTO> itens,
        List<AutorDTO> autores
        ) {
}
