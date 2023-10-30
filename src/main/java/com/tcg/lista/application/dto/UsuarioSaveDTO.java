package com.tcg.lista.application.dto;

import com.tcg.lista.domain.enitty.usuario.UsuarioStatus;

import java.time.LocalDate;

public record UsuarioSaveDTO(
        String nome,
        LocalDate dataNascimento,
        String email,
        String cpf,
        String senha,
        UsuarioStatus status
) {
}
