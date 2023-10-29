package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.notificacao.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

}


