package com.tcg.lista.application.dto;

import com.tcg.lista.domain.amizade.AmizadeStatus;

public record AmizadeSaveDTO (
        Long usuarioId,
        Long amigoId,
        AmizadeStatus status
){

}
