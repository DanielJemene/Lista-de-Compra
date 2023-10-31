package com.tcg.lista.application.dto;

import com.tcg.lista.domain.entity.usuario.UsuarioStatus;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsuarioSaveDTO(

        @NotBlank(message = "O nome não pode estar vazio.")
        String nome,

        @Past(message = "A data de nascimento deve ser no passado")
        LocalDate dataNascimento,

        @NotBlank(message = "O Email não pode estar vazio.")
        String email,

        @CPF
        String cpf,

        @NotBlank(message = "A Senha não pode estar vazia.")
        String senha,

        @NotNull(message = "O status do usuário não pode ser nulo.")
        @Enumerated
        UsuarioStatus status
) {
}
