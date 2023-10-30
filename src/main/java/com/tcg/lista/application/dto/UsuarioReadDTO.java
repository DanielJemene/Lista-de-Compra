package com.tcg.lista.application.dto;

import com.tcg.lista.domain.enitty.usuario.UsuarioStatus;

import java.time.LocalDate;
import java.util.List;

public record UsuarioReadDTO(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String email,
        String cpf,
        UsuarioStatus status,
        List<AmizadeReadDTO> amigos
) {
}
