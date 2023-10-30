package com.tcg.lista.application.dto;

import com.tcg.lista.domain.enitty.usuario.UsuarioStatus;

public record UsuarioSaveDTO(
        String nome,
        String email,
        String cpf,
        String senha,
        UsuarioStatus status
) {
}
