package com.tcg.lista.application.dto;

import com.tcg.lista.domain.enitty.usuario.UsuarioStatus;

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
