package com.tcg.lista.domain.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tcg.lista.domain.amizade.Amizade;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String cpf;

    @Column
    private String senha;

    @Column
    private int status;

    @OneToMany
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    @JsonIgnoreProperties({"usuario"})
    private List<Amizade> amigos;

    public Usuario(Long id){
        this.id = id;
    }

}
