package com.tcg.lista.application.controller;

import com.tcg.lista.application.dto.AutorDTO;
import com.tcg.lista.domain.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listas/autores")
public class AutorController {

    @Autowired
    private AutorService service;

    @PostMapping
    public AutorDTO create(@RequestBody @Valid AutorDTO autorDTO){
        return service.create(autorDTO);
    }

    @GetMapping("/{id}")
    public AutorDTO getById(@PathVariable Long id){
        return service.getAutorDTOById(id);
    }

    @GetMapping("/bylista/{id}")
    public List<AutorDTO> getAllAutoresByListaId(@PathVariable Long id){
        return service.getAllAutoresByListaId(id);
    }

    @PutMapping("/{id}")
    public AutorDTO update(@PathVariable Long id, @RequestBody @Valid AutorDTO autorDTO){
        return service.update(id, autorDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
