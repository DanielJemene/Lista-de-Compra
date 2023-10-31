package com.tcg.lista.domain.service;

import com.tcg.lista.application.dto.ItemDTO;
import com.tcg.lista.application.exception.EntityNotFoundException;
import com.tcg.lista.domain.entity.categoria.Categoria;
import com.tcg.lista.domain.entity.item.Item;
import com.tcg.lista.domain.entity.lista.Lista;
import com.tcg.lista.domain.entity.usuario.Usuario;
import com.tcg.lista.infraestructure.mysql.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repo;


    public ItemDTO addItem(Lista lista, ItemDTO itemDTO){
        var item = toEntity(itemDTO);
        item.setLista(lista);

        return toDTO(repo.save(item));
    }

    public ItemDTO updateItem(Long id, ItemDTO itemDTO, Lista lista){

        var item = repo.getReferenceById(id);

        item.setUsuario(new Usuario(itemDTO.usuarioId()));
        item.setNome(itemDTO.nome());
        item.setQuantidade(itemDTO.quantidade());
        item.setPreco(itemDTO.preco());
        item.setDescricao(itemDTO.descricao());
        item.setConcluido(itemDTO.isConcluido());

        if (itemDTO.isConcluido() && item.getDataConclusao() == null) {
            item.setDataConclusao(LocalDateTime.now());
        }

        Item itemAtualizado = repo.save(item);
        this.isTodosItensFinalizados(lista);

        return toDTO(itemAtualizado);
    }

    public ItemDTO getItemById(Long id){
        return toDTO(repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Item")));
    }

    public boolean isTodosItensFinalizados(Lista lista) {
        if (lista.getItens().stream().allMatch(item -> item.isConcluido())){
            lista.setConcluida(true);
            return true;
        }
        lista.setConcluida(false);
        return false;
    }

    public List<ItemDTO> getItemByListaId(Long id){
        return repo.findItemByListaId(id)
                .orElseThrow(()-> new RuntimeException("A Lista não possui Itens."))
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void deleteItem(Long id){
        repo.deleteById(id);
    }

    public Item toEntity(ItemDTO itemDTO){
        return new Item(
                itemDTO.id(),
                new Usuario(itemDTO.usuarioId()),
                new Lista(itemDTO.listaId()),
                new Categoria(itemDTO.categoriaId()),
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
                item.getCategoria().getId(),
                item.getNome(),
                item.getQuantidade(),
                item.getPreco(),
                item.getDescricao(),
                item.isConcluido(),
                item.getDataConclusao());
    }
}
