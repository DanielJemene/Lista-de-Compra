package com.tcg.lista.application.controller;

import com.tcg.lista.application.dto.NotificacaoDTO;
import com.tcg.lista.domain.service.NotificacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
        return notificacaoService.getNotificacaoDTO(id);
    }

    @Transactional
    @PostMapping
    public NotificacaoDTO createNotificacao(@RequestBody @Valid NotificacaoDTO notificacaoDTO) {
        return notificacaoService.createNotificacao(notificacaoDTO);
    }

    @Transactional
    @PutMapping()
    public NotificacaoDTO updateNotificacao(@RequestBody @Valid NotificacaoDTO notificacaoDTO) {
        return notificacaoService.updateNotificacao(notificacaoDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarNotificacao(@PathVariable Long id) {
        notificacaoService.deletarNotificacao(id);
    }

}

