package com.tcg.lista.application.controller;

import com.tcg.lista.domain.services.NotificacaoService;
import com.tcg.lista.domain.notificacao.Notificacao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping
    public List<Notificacao> getAllNotificacoes() {
        return notificacaoService.getAllNotificacoes();
    }

    @GetMapping("/{id}")
    public Notificacao getNotificacao(@PathVariable Long id) {
        return notificacaoService.getNotificacao(id);
    }

    @Transactional
    @PostMapping
    public Notificacao createNotificacao(@RequestBody Notificacao notificacao) {
        return notificacaoService.createNotificacao(notificacao);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarNotificacao(@PathVariable Long id) {
        notificacaoService.deletarNotificacao(id);
    }

}

