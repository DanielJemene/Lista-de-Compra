package com.tcg.lista.domain.item;

import com.tcg.lista.domain.lista.Lista;
import com.tcg.lista.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "lista_id", referencedColumnName = "lista_id")
    private Lista lista;

    private String nome;

    private int quantidade;

    private BigDecimal preco;

    private String descricao;

    private boolean isConcluido;

    private LocalDateTime dataConclusao;
}
