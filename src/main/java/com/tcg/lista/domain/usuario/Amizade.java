package com.tcg.lista.domain.usuario;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "amizade")
public class Amizade {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID amizade_id;

    @OneToOne
    @JoinColumn(name = "amigo_id", referencedColumnName = "usuario_id")
    private Usuario amigo;

    @Column
    private LocalDate data_inicio;

    @Column
    private LocalDate data_fim;

    @Column
    private Boolean status;

}
