package com.tcg.lista.domain.services;

import com.tcg.lista.application.dto.NotificacaoDTO;
import com.tcg.lista.application.exception.BusinessException;
import com.tcg.lista.application.exception.EntityNotFoundException;
import com.tcg.lista.domain.enitty.notificacao.Notificacao;
import com.tcg.lista.domain.enitty.notificacao.NotificacaoStatus;
import com.tcg.lista.domain.enitty.notificacao.TipoNotificacao;
import com.tcg.lista.domain.enitty.usuario.Usuario;
import com.tcg.lista.infraestructure.mysql.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<NotificacaoDTO> getAllNotificacoesByDestinatarioId(Long id) {

        return notificacaoRepository.findNotificacaoByDestinatarioId(id)
                .orElseThrow(()-> new BusinessException("Usuário não possui Notificações."))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Notificacao getNotificacao(Long id) {
        return notificacaoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Notificação"));
    }

    public NotificacaoDTO getNotificacaoDTO(Long id) {
        return toDTO(getNotificacao(id));
    }

    public NotificacaoDTO createNotificacao(NotificacaoDTO notificacaoDTO) {
        return toDTO(notificacaoRepository.save(toEntity(notificacaoDTO)));
    }

    public NotificacaoDTO updateNotificacao(NotificacaoDTO notificacaoDTO) {

        var notificacao = getNotificacao(notificacaoDTO.id());

        notificacao.setMensagem(notificacaoDTO.mensagem());
        notificacao.setTipo(notificacaoDTO.tipo().getValue());
        notificacao.setStatus(notificacaoDTO.status().getValue());

        return toDTO(notificacaoRepository.save(notificacao));
    }

    public void deletarNotificacao(Long id) {
        notificacaoRepository.deleteById(id);
    }

    private NotificacaoDTO toDTO(Notificacao notificacao){
        return new NotificacaoDTO(
                notificacao.getId(),
                notificacao.getEmissor().getId(),
                notificacao.getDestinatario().getId(),
                notificacao.getMensagem(),
                TipoNotificacao.fromValue(notificacao.getTipo()),
                NotificacaoStatus.fromValue(notificacao.getStatus())
        );
    }

    private Notificacao toEntity(NotificacaoDTO notificacaoDTO){

        return new Notificacao(
                notificacaoDTO.id(),
                usuarioService.getUsuario(notificacaoDTO.emissorId()),
                usuarioService.getUsuario(notificacaoDTO.destinatarioId()),
                notificacaoDTO.mensagem(),
                notificacaoDTO.tipo().getValue(),
                (notificacaoDTO.status() == null ?
                        NotificacaoStatus.NAO_LIDA.getValue() : notificacaoDTO.status().getValue())
        );
    }
}
