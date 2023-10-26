package com.tcg.lista.domain.services;

import com.tcg.lista.domain.notificacao.Notificacao;
import com.tcg.lista.infraestructure.mysql.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<Notificacao> getAllNotificacoes() {
        return notificacaoRepository.findAll();
    }

    public Notificacao getNotificacao(Long id) {
        return notificacaoRepository.findById(id).orElse(null);
    }

    public Notificacao createNotificacao(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public void deletarNotificacao(Long id) {
        notificacaoRepository.deleteById(id);
    }

}
