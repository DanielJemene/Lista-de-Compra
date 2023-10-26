package com.tcg.lista.domain.usuario;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuario_id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String cpf;

    @Column
    private String senha;

    @Column
    private Boolean status;

    @OneToMany
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private List<Amizade> amigos;

}
