package com.tcg.lista.domain.services;

import com.tcg.lista.domain.produto.Produto;
import com.tcg.lista.infraestructure.mysql.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto getProduto(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public Produto createProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto updateProduto(Long id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setId(id);
            return produtoRepository.save(produto);
        }
        return null;
    }

    public Produto getByNome(String nome){
        return produtoRepository.findByNome(nome);
    }

    public void deleteProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}

