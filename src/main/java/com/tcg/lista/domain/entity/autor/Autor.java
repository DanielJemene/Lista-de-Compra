package com.tcg.lista.domain.entity.autor;

import com.tcg.lista.domain.entity.lista.Lista;
import com.tcg.lista.domain.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autor_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "lista_id", referencedColumnName = "lista_id")
    private Lista lista;

    private int status;

    public Autor(Long id){
        this.id = id;
    }
}
