package com.tcg.lista.domain.services;

import com.tcg.lista.application.dto.ItemDTO;
import com.tcg.lista.domain.item.Item;
import com.tcg.lista.domain.lista.Lista;
import com.tcg.lista.domain.usuario.Usuario;
import com.tcg.lista.infraestructure.mysql.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemService {

    private ItemRepository repo;

    public ItemDTO addItem(Lista lista, ItemDTO itemDTO){

        var item = toEntity(itemDTO);
        item.setLista(lista);

        return toDTO(repo.save(item));
    }

    public ItemDTO updateItem(Long itemId, ItemDTO itemDTO){

        var item = repo.getReferenceById(itemId);

        item.setUsuario(new Usuario(itemDTO.usuarioId()));
        item.setNome(itemDTO.nome());
        item.setQuantidade(itemDTO.quantidade());
        item.setPreco(itemDTO.preco());
        item.setDescricao(itemDTO.descricao());
        item.setConcluido(itemDTO.isConcluido());

        if (itemDTO.isConcluido() && item.getDataConclusao() == null) {
            item.setDataConclusao(LocalDateTime.now());
        }

        return toDTO(repo.save(item));
    }

    public Item toEntity(ItemDTO itemDTO){
        return new Item(
                itemDTO.id(),
                new Usuario(itemDTO.usuarioId()),
                new Lista(itemDTO.listaId()),
                itemDTO.nome(),
                itemDTO.quantidade(),
                itemDTO.preco(),
                itemDTO.descricao(),
                itemDTO.isConcluido(),
                itemDTO.dataConclusao());
    }

    public ItemDTO toDTO(Item item){
        return new ItemDTO(
                item.getId(),
                item.getUsuario().getId(),
                item.getLista().getId(),
                item.getNome(),
                item.getQuantidade(),
                item.getPreco(),
                item.getDescricao(),
                item.isConcluido(),
                item.getDataConclusao());
    }
}
