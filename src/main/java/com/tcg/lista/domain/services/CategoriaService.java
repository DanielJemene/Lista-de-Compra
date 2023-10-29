package com.tcg.lista.domain.services;

import com.tcg.lista.application.dto.CategoriaDTO;
import com.tcg.lista.domain.categoria.Categoria;
import com.tcg.lista.domain.categoria.CategoriaStatus;
import com.tcg.lista.domain.usuario.Usuario;
import com.tcg.lista.infraestructure.mysql.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CategoriaDTO getCategoriaById(Long id) {
        return toDTO(categoriaRepository.findById(id).orElse(null));
    }

    public CategoriaDTO createCategoria(CategoriaDTO categoriaDTO) {
        return toDTO(categoriaRepository.save(toEntity(categoriaDTO)));
    }

    public CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        if (categoriaRepository.existsById(id)) {

            var categoria = categoriaRepository.getReferenceById(id);
            categoria.setNome(categoriaDTO.nome());
            categoria.setUsuario(new Usuario(categoriaDTO.usuarioId()));
            categoria.setStatus(categoriaDTO.status().getValue());

            return toDTO(categoriaRepository.save(categoria));
        }
        return null;
    }

    public void deleteCategoriaById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public CategoriaDTO toDTO(Categoria categoria){
        return new CategoriaDTO(
                categoria.getId(),
                categoria.getUsuario().getId(),
                categoria.getNome(),
                CategoriaStatus.fromValue(categoria.getStatus())
        );
    }

    private Categoria toEntity(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.id(),
                new Usuario(categoriaDTO.usuarioId()),
                categoriaDTO.nome(),
                categoriaDTO.status().getValue());
    }
}
