package com.tcg.lista.domain.enitty.item;

import com.tcg.lista.domain.enitty.categoria.Categoria;
import com.tcg.lista.domain.enitty.lista.Lista;
import com.tcg.lista.domain.enitty.usuario.Usuario;
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

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    private Categoria categoria;

    private String nome;

    private int quantidade;

    private BigDecimal preco;

    private String descricao;

    private boolean isConcluido;

    private LocalDateTime dataConclusao;
}
