package com.tcg.lista.application.dto;

import com.tcg.lista.domain.enitty.amizade.AmizadeStatus;

import java.time.LocalDate;

public record AmizadeReadDTO(
        Long id,
        Long usuarioId,
        Long amigoId,
        LocalDate dataInicio,
        LocalDate dataFim,
        AmizadeStatus status
) {
}
