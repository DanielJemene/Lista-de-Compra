package com.tcg.lista.application.dto;

import com.tcg.lista.domain.enitty.notificacao.NotificacaoStatus;
import com.tcg.lista.domain.enitty.notificacao.TipoNotificacao;

public record NotificacaoDTO(
        Long id,
        Long emissorId,
        Long destinatarioId,
        String mensagem,
        TipoNotificacao tipo,
        NotificacaoStatus status
) {
}
