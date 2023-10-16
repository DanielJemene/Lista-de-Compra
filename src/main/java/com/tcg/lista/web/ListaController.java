package com.tcg.lista.web;

import com.tcg.lista.application.ListaService;
import com.tcg.lista.application.ProdutoService;
import com.tcg.lista.domain.lista.Lista;
import com.tcg.lista.domain.produto.Produto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Lista> getAllListas() {
        return listaService.getAllListas();
    }

    @GetMapping("/{id}")
    public Lista getLista(@PathVariable Long id) {
        return listaService.getListaById(id);
    }

    @Transactional
    @PostMapping
    public Lista createLista(@RequestBody Lista lista) {
        return listaService.createLista(lista);
    }

    @Transactional
    @PutMapping("/{id}")
    public Lista updateLista(@PathVariable Long id, @RequestBody Lista lista) {
        return listaService.updateLista(id, lista);
    }

    @Transactional
    @PostMapping("/adicionarProduto")
    public Lista adicionarProduto(@RequestParam(name = "nomeLista") String nomeLista,
                                  @RequestParam(name = "nomeProduto") String nomeProduto){
        Lista lista = listaService.getListaByNome(nomeLista);
        Produto produto = produtoService.getByNome(nomeProduto);

        lista.getProdutos().add(produto);
        return lista;
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteLista(@PathVariable Long id) {
        listaService.deleteLista(id);
    }
}

