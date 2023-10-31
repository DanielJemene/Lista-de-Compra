package com.tcg.lista.application.dto;

import com.tcg.lista.domain.entity.categoria.CategoriaStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;


public record CategoriaDTO(
        Long id,

        @NotNull(message = "O usuarioId não pode ser nulo")
        @Min(value = 1, message = "O usuarioId não pode ser zero ou negativo")
        Long usuarioId,


        @NotBlank(message = "O nome não pode estar vazio.")
        @NotEmpty(message = "O nome não pode estar vazio.")
        String nome,

        @NotNull(message = "O status da categoria não pode ser nulo.")
        @Enumerated
        CategoriaStatus status) {
}
