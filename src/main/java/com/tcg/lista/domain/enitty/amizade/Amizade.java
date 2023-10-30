package com.tcg.lista.domain.enitty.amizade;

import com.tcg.lista.domain.enitty.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "amizade")
public class Amizade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amizade_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "amigo_id", referencedColumnName = "usuario_id")
    private Usuario amigo;

    @Column
    private LocalDate dataInicio;

    @Column
    private LocalDate dataFim;

    @Column
    private int status;

}
