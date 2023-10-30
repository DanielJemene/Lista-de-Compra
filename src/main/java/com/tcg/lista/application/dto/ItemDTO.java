package com.tcg.lista.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ItemDTO(
        Long id,
        Long usuarioId,
        Long listaId,
        Long categoriaId,
        String nome,
        int quantidade,
        BigDecimal preco,
        String descricao,
        boolean isConcluido,
        LocalDateTime dataConclusao
) {
}
