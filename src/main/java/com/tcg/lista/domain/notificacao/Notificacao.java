package com.tcg.lista.domain.notificacao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tcg.lista.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificacao_id;

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @JsonIgnoreProperties({"amigos"})
    private Usuario usuario;

    @Column
    private String mensagem;

    @Column
    private Boolean status;



}
