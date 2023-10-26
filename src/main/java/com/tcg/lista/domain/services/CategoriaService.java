package com.tcg.lista.domain.services;

import com.tcg.lista.domain.categoria.Categoria;
import com.tcg.lista.infraestructure.mysql.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setCategoria_id(id);
            return categoriaRepository.save(categoria);
        }
        return null;
    }

    public void deleteCategoriaById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
