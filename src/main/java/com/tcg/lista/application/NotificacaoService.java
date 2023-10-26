package com.tcg.lista.application;

import com.tcg.lista.domain.notificacao.Notificacao;
import com.tcg.lista.domain.notificacao.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
