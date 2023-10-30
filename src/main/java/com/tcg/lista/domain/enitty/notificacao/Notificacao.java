package com.tcg.lista.domain.enitty.notificacao;

import com.tcg.lista.domain.enitty.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notificacao_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emissor_id", referencedColumnName = "usuario_id")
    private Usuario emissor;

    @ManyToOne
    @JoinColumn(name = "destinatario_id", referencedColumnName = "usuario_id")
    private Usuario destinatario;

    @Column
    private String mensagem;

    @Column
    private int tipo;

    @Column
    private int status;



}
