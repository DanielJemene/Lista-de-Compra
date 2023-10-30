package com.tcg.lista.domain.entity.lista;

import com.tcg.lista.domain.entity.autor.Autor;
import com.tcg.lista.domain.entity.item.Item;
import com.tcg.lista.domain.entity.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Lista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lista_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    private String nome;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataValidade;

    private LocalDateTime dataConclusao;

    private boolean isConcluida;

    @OneToMany
    @JoinColumn(name = "lista_id")
    private List<Item> itens = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "lista_id")
    private List<Autor> autores = new ArrayList<>();

    public Lista(Long id){
        this.id = id;
    }

    public BigDecimal getPrecoTotal() {
        return getItens().stream()
                .map(Item::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Item> getItens() {
        if (itens == null) {
            itens = new ArrayList<>();
        }
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public List<Autor> getAutores(){
        if (autores == null) {
            autores = new ArrayList<>();
        }
        return  autores;
    }
}
