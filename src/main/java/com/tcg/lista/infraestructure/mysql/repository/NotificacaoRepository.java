package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.enitty.notificacao.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    Optional<List<Notificacao>> findNotificacaoByDestinatarioId(@Param("destinatario_id") Long id);
}


