package com.tcg.lista.application.dto;

import com.tcg.lista.domain.enitty.amizade.AmizadeStatus;

public record AmizadeSaveDTO (
        Long usuarioId,
        Long amigoId,
        AmizadeStatus status
){

}
