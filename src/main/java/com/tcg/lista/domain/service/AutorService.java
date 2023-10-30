package com.tcg.lista.domain.service;

import com.tcg.lista.application.dto.AutorDTO;
import com.tcg.lista.application.exception.BusinessException;
import com.tcg.lista.application.exception.EntityNotFoundException;
import com.tcg.lista.domain.entity.autor.Autor;
import com.tcg.lista.domain.entity.autor.AutorStatus;
import com.tcg.lista.infraestructure.mysql.repository.AutorRepository;
import com.tcg.lista.infraestructure.mysql.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repo;

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public AutorDTO create(AutorDTO autorDTO){
        return toDTO(repo.save(toEntity(autorDTO)));
    }

    public AutorDTO update(Long id, AutorDTO autorDTO){

        var autor = getById(id);

        autor.setUsuario(usuarioService.getUsuario(autorDTO.usuarioId()));
        autor.setLista(listaRepository.findById(autorDTO.listaId()).orElseThrow(() -> new EntityNotFoundException("Lista")));
        autor.setStatus(autorDTO.status().getValue());

        return toDTO(repo.save(autor));
    }

    public Autor getById(Long id){
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Autor"));
    }

    public AutorDTO getAutorDTOById(Long id){
        return toDTO(getById(id));
    }

    public List<AutorDTO> getAllAutoresByListaId(Long id){
        return repo.findAutorByListaId(id).
                orElseThrow(() -> new BusinessException("A Lista não possui Autores."))
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
                usuarioService.getUsuario(autorDTO.usuarioId()),
                listaRepository.findById(autorDTO.listaId()).orElseThrow(() -> new EntityNotFoundException("Lista")),
                autorDTO.status() == null ? AutorStatus.ATIVO.getValue() : autorDTO.status().getValue()
        );
    }
}
