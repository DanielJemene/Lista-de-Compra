package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByNome(String nome);
}


