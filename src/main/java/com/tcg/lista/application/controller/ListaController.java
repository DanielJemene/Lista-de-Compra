package com.tcg.lista.application.controller;

import com.tcg.lista.application.dto.ItemDTO;
import com.tcg.lista.application.dto.ListaDTO;
import com.tcg.lista.domain.services.ListaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @GetMapping("/byuser/{id}")
    public List<ListaDTO> getAllListasByUser(@PathVariable Long id) {
        return listaService.getAllListasByUser(id);
    }

    @GetMapping("/{id}")
    public ListaDTO getLista(@PathVariable Long id) {
        return listaService.getListaById(id);
    }

    @Transactional
    @PostMapping
    public ListaDTO createLista(@RequestBody ListaDTO listaDTO) {
        return listaService.createLista(listaDTO);
    }

    @Transactional
    @PutMapping("/{id}")
    public ListaDTO updateLista(@PathVariable Long id, @RequestBody ListaDTO listaDTO) {
        return listaService.updateLista(id, listaDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteLista(@PathVariable Long id) {
        listaService.deleteLista(id);
    }

    @Transactional
    @PostMapping("/{id}/item")
    public ItemDTO addItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        return listaService.addItem(id, itemDTO);
    }

    @Transactional
    @PutMapping("/item/{id}")
    public ItemDTO updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        return listaService.updateItem(id, itemDTO);
    }
}

