package com.tcg.lista.application.dto;

import com.tcg.lista.domain.entity.usuario.UsuarioStatus;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioSaveDTO(

        @NotBlank(message = "O nome não pode estar vazio.")
        String nome,
        @NotBlank(message = "A Data de Nascimento não pode estar vazia.")
        LocalDate dataNascimento,
        @NotBlank(message = "O Email não pode estar vazio.")
        String email,
        @CPF
        String cpf,
        @NotBlank(message = "A Senha não pode estar vazia.")
        String senha,
        UsuarioStatus status
) {
}
