package com.tcg.lista.application.dto;

import com.tcg.lista.domain.amizade.Amizade;
import com.tcg.lista.domain.usuario.UsuarioStatus;

import java.util.List;

public record UsuarioReadDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        UsuarioStatus status,
        List<AmizadeReadDTO> amigos
) {
}
