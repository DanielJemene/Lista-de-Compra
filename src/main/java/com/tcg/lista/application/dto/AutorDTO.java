package com.tcg.lista.application.dto;

import com.tcg.lista.domain.entity.autor.AutorStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;


@Validated
public record AutorDTO(
        Long id,

        @NotNull(message = "O usuarioId não pode ser nulo")
        @Min(value = 1, message = "O usuarioId não pode ser zero ou negativo")
        Long usuarioId,


        String nome,

        @NotNull(message = "O campo listaId não pode ser nulo")
        @Min(value = 1, message = "O campo listaId não pode ser zero ou negativo")
        Long listaId,

        @NotNull(message = "O status do autor não pode ser nulo.")
        @Enumerated
        AutorStatus status
) {
}
