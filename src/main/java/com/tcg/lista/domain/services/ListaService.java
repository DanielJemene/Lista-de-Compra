package com.tcg.lista.domain.services;

import com.tcg.lista.application.dto.ItemDTO;
import com.tcg.lista.application.dto.ListaDTO;
import com.tcg.lista.domain.item.Item;
import com.tcg.lista.domain.lista.Lista;
import com.tcg.lista.domain.usuario.Usuario;
import com.tcg.lista.infraestructure.mysql.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ItemService itemService;

    public List<ListaDTO> getAllListasByUser(Long id) {
        return listaRepository.findListaByUsuarioId(id)
                .orElseThrow(()-> new RuntimeException("Usuário não possui Listas."))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ListaDTO getListaById(Long id) {
        return toDTO(listaRepository.findById(id).orElseThrow(()-> new RuntimeException("Lista não encontrada.")));
    }

    public ListaDTO createLista(ListaDTO listaDTO) {

        var lista = toEntity(listaDTO);
        lista.setDataCriacao(LocalDateTime.now());

        return toDTO(listaRepository.save(lista));
    }

    public ListaDTO updateLista(Long id, ListaDTO listaDTO) {

        var lista = listaRepository.getReferenceById(id);

        lista.setNome(listaDTO.nome());
        lista.setUsuario(new Usuario(listaDTO.usuarioId()));
        lista.setDataCriacao(listaDTO.dataCriacao());
        lista.setDataValidade(listaDTO.dataValidade());
        lista.setConcluida(listaDTO.isConcluida());

        if (listaDTO.isConcluida() && listaDTO.dataConclusao() == null) {
            lista.setDataConclusao(LocalDateTime.now());
        }

        return toDTO(listaRepository.save(lista));
    }

    public void deleteLista(Long id) {
        listaRepository.deleteById(id);
    }

    public ItemDTO addItem(Long listaId, ItemDTO itemDTO) {

        var lista = listaRepository.getReferenceById(listaId);

        return itemService.addItem(lista, itemDTO);

    }

    public ItemDTO updateItem(Long itemId, ItemDTO itemDTO) {
        return itemService.updateItem(itemId, itemDTO);
    }

    private Lista toEntity(ListaDTO listaDTO){


        var lista = new Lista(
                listaDTO.id(),
                new Usuario(listaDTO.usuarioId()),
                listaDTO.nome(),
                listaDTO.dataCriacao(),
                listaDTO.dataValidade(),
                listaDTO.dataConclusao(),
                listaDTO.isConcluida(),
                null);

        if (listaDTO.itens() != null) {
            lista.setItens(listaDTO.itens().stream().map(itemService::toEntity).collect(Collectors.toList()));
        }

        return lista;
    }

    private ListaDTO toDTO(Lista lista){
       return new ListaDTO(
               lista.getId(),
               lista.getUsuario().getId(),
               lista.getNome(),
               lista.getDataCriacao(),
               lista.getDataValidade(),
               lista.getDataConclusao(),
               lista.isConcluida(),
               lista.getItens().stream().map(itemService::toDTO).collect(Collectors.toList())
       );
    }
}
