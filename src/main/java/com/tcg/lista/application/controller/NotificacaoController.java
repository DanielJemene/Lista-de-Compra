package com.tcg.lista.application.controller;

import com.tcg.lista.application.dto.NotificacaoDTO;
import com.tcg.lista.domain.services.NotificacaoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacoes")
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/byuser/{id}")
    public List<NotificacaoDTO> getAllNotificacoes(@PathVariable Long id) {
        return notificacaoService.getAllNotificacoesByDestinatarioId(id);
    }

    @GetMapping("/{id}")
    public NotificacaoDTO getNotificacao(@PathVariable Long id) {
        return notificacaoService.getNotificacao(id);
    }

    @Transactional
    @PostMapping
    public NotificacaoDTO createNotificacao(@RequestBody NotificacaoDTO notificacaoDTO) {
        return notificacaoService.createNotificacao(notificacaoDTO);
    }

    @Transactional
    @PutMapping("/{id}")
    public NotificacaoDTO updateNotificacao(@PathVariable Long id, @RequestBody NotificacaoDTO notificacaoDTO) {
        return notificacaoService.updateNotificacao(id, notificacaoDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarNotificacao(@PathVariable Long id) {
        notificacaoService.deletarNotificacao(id);
    }

}

