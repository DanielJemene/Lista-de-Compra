package com.tcg.lista.application.dto;

import com.tcg.lista.domain.entity.notificacao.NotificacaoStatus;
import com.tcg.lista.domain.entity.notificacao.TipoNotificacao;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificacaoDTO(
        Long id,

        @NotNull(message = "O emissorId não pode ser nulo")
        @Min(value = 1, message = "O emissorId não pode ser zero ou negativo")
        Long emissorId,

        @NotNull(message = "O destinatarioId não pode ser nulo")
        @Min(value = 1, message = "O destinatarioId não pode ser zero ou negativo")
        Long destinatarioId,

        @NotNull(message = "A mensagem não pode ser nula.")
        @NotBlank(message = "A mensagem não pode estar vazia.")
        String mensagem,

        @NotNull(message = "O tipo da notificação não pode ser nulo.")
        @Enumerated
        TipoNotificacao tipo,

        @NotNull(message = "O status da notificação não pode ser nulo.")
        @Enumerated
        NotificacaoStatus status
) {
}
