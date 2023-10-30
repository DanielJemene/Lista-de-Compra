package com.tcg.lista.domain.services;

import com.tcg.lista.application.dto.ItemDTO;
import com.tcg.lista.application.dto.ListaDTO;
import com.tcg.lista.application.exception.BusinessException;
import com.tcg.lista.application.exception.EntityNotFoundException;
import com.tcg.lista.domain.enitty.lista.Lista;
import com.tcg.lista.domain.enitty.usuario.Usuario;
import com.tcg.lista.infraestructure.mysql.repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    @Autowired
    private ItemService itemService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private UsuarioService usuarioService;

    public List<ListaDTO> getAllListasByUser(Long id) {
        return listaRepository.findListaByUsuarioId(id)
                .orElseThrow(()-> new BusinessException("Usuário não possui Listas."))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ListaDTO getListaDTOById(Long id) {
        return toDTO(getListaById(id));
    }

    public Lista getListaById(Long id) {
        return listaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Lista"));
    }

    public ListaDTO createLista(ListaDTO listaDTO) {

        var lista = toEntity(listaDTO);
        lista.setDataCriacao(LocalDateTime.now());

        return toDTO(listaRepository.save(lista));
    }

    public ListaDTO updateLista(Long id, ListaDTO listaDTO) {

        var lista = getListaById(id);

        lista.setNome(listaDTO.nome());
        lista.setUsuario(usuarioService.getUsuario(listaDTO.usuarioId()));
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

    public ItemDTO addItem(ItemDTO itemDTO) {
        return itemService.addItem(getListaById(itemDTO.listaId()), itemDTO);
    }

    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {
        return itemService.updateItem(id, itemDTO);
    }

    public ItemDTO getItemById(Long id) {
        return itemService.getItemById(id);
    }

    public List<ItemDTO> getItemByListaId(Long id) {
        return itemService.getItemByListaId(id);
    }

    public void deleteItem(Long id) {
        itemService.deleteItem(id);
    }
    private Lista toEntity(ListaDTO listaDTO){


        var lista = new Lista(
                listaDTO.id(),
                usuarioService.getUsuario(listaDTO.usuarioId()),
                listaDTO.nome(),
                listaDTO.dataCriacao(),
                listaDTO.dataValidade(),
                listaDTO.dataConclusao(),
                listaDTO.isConcluida(),
                null,
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
               lista.getPrecoTotal(),
               lista.getDataCriacao(),
               lista.getDataValidade(),
               lista.getDataConclusao(),
               lista.isConcluida(),
               lista.getItens().stream().map(itemService::toDTO).collect(Collectors.toList()),
               lista.getAutores().stream().map(autorService::toDTO).collect(Collectors.toList())
       );
    }
}
