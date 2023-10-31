package com.tcg.lista.application.dto;

import com.tcg.lista.domain.entity.amizade.AmizadeStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AmizadeSaveDTO (

        @NotNull(message = "O usuarioId não pode ser nulo")
        @Min(value = 1, message = "O usuarioId não pode ser zero ou negativo")
        Long usuarioId,

        @NotNull(message = "O amigoId não pode ser nulo")
        @Min(value = 1, message = "O amigoId não pode ser zero ou negativo")
        Long amigoId,

        @NotNull(message = "O status de amizade não pode ser nulo.")
        @Enumerated
        AmizadeStatus status
){

}
