package com.tcg.lista.domain.services;

import com.tcg.lista.application.dto.AutorDTO;
import com.tcg.lista.application.exception.EntityNotFoundException;
import com.tcg.lista.domain.enitty.autor.Autor;
import com.tcg.lista.domain.enitty.autor.AutorStatus;
import com.tcg.lista.domain.enitty.lista.Lista;
import com.tcg.lista.domain.enitty.usuario.Usuario;
import com.tcg.lista.infraestructure.mysql.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repo;

    public AutorDTO create(AutorDTO autorDTO){
        return toDTO(repo.save(toEntity(autorDTO)));
    }

    public AutorDTO update(Long id, AutorDTO autorDTO){

        var autor = repo.getReferenceById(id);

        autor.setUsuario(new Usuario(autorDTO.usuarioId()));
        autor.setLista(new Lista(autorDTO.listaId()));
        autor.setStatus(autorDTO.status().getValue());

        return toDTO(repo.save(autor));
    }

    public AutorDTO getById(Long id){
        return toDTO(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Autor")));
    }

    public List<AutorDTO> getAllAutoresByListaId(Long id){
        return repo.findAutorByListaId(id).
                orElseThrow(() -> new RuntimeException("Autor não encontrado."))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public AutorDTO toDTO(Autor autor){
        return new AutorDTO(
                autor.getId(),
                autor.getUsuario().getId(),
                autor.getUsuario().getNome(),
                autor.getLista().getId(),
                AutorStatus.fromValue(autor.getStatus())
        );
    }

    public Autor toEntity(AutorDTO autorDTO){
        return new Autor(
                autorDTO.id(),
                new Usuario(autorDTO.usuarioId()),
                new Lista(autorDTO.listaId()),
                autorDTO.status() == null ? AutorStatus.ATIVO.getValue() : autorDTO.status().getValue()
        );
    }
}
