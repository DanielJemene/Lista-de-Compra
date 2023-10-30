package com.tcg.lista.application.controller;

import com.tcg.lista.application.dto.CategoriaDTO;
import com.tcg.lista.domain.service.CategoriaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaDTO> getAllListas() {
        return categoriaService.getAllCategorias();
    }

    @GetMapping("/{id}")
    public CategoriaDTO getCategoriaById(@PathVariable Long id) {
        return categoriaService.getCategoriaById(id);
    }

    @Transactional
    @PostMapping
    public CategoriaDTO createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.createCategoria(categoriaDTO);
    }

    @Transactional
    @PutMapping("/{id}")
    public CategoriaDTO updateProduto(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        return categoriaService.updateCategoria(id, categoriaDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoriaById(id);
    }
}

