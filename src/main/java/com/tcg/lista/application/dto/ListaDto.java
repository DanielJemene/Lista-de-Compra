package com.tcg.lista.application.dto;

import lombok.*;

import java.util.Date;

public record ListaDto(Long id, Date dataDeCriacao, String nome, boolean isConcluida) {
}
