package com.tcg.lista.application.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ListaDTO(
        Long id,

        @NotNull(message = "O usuarioId não pode ser nulo")
        @Min(value = 1, message = "O usuarioId não pode ser zero ou negativo")
        Long usuarioId,

        @NotBlank(message = "O nome não pode estar vazio.")
        String nome,

        BigDecimal precoTotal,
        LocalDateTime dataCriacao,

        @NotNull(message = "A data de validade não pode ser nula")
        @Future(message = "A data de validade deve ser no futuro")
        LocalDateTime dataValidade,
        LocalDateTime dataConclusao,
        boolean isConcluida,
        List<ItemDTO> itens,
        List<AutorDTO> autores
        ) {
}
