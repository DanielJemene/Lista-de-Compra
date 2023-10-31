package com.tcg.lista.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ItemDTO(
        Long id,

        @NotNull(message = "O usuarioId não pode ser nulo")
        @Min(value = 1, message = "O usuarioId não pode ser zero ou negativo")
        Long usuarioId,

        @NotNull(message = "O campo listaId não pode ser nulo")
        @Min(value = 1, message = "O campo listaId não pode ser zero ou negativo")
        Long listaId,

        @NotNull(message = "O campo categoriaId não pode ser nulo")
        @Min(value = 1, message = "O campo categoriaId não pode ser zero ou negativo")
        Long categoriaId,

        @NotBlank(message = "O nome não pode estar vazio.")
        String nome,

        @NotNull(message = "O campo quantidade não pode ser nulo")
        @Min(value = 1, message = "O campo quantidade não pode ser zero ou negativo")
        int quantidade,

        @NotNull(message = "O campo preco não pode ser nulo")
        @DecimalMin(value = "0.0", inclusive = false, message = "O preco não pode ser menor ou igual a 0")
        BigDecimal preco,

        String descricao,
        boolean isConcluido,
        LocalDateTime dataConclusao
) {
}
