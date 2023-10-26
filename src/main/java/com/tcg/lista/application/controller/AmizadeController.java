package com.tcg.lista.application.controller;

import com.tcg.lista.domain.services.AmizadeService;
import com.tcg.lista.domain.usuario.Amizade;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amizade")
public class AmizadeController {

    @Autowired
    private AmizadeService amizadeService;

    @GetMapping
    public List<Amizade> getAllAmizades() {
        return amizadeService.getAllAmizades();
    }

    @GetMapping("/{id}")
    public Amizade getAmizade(@PathVariable Long id) {
        return amizadeService.getAmizade(id);
    }

    @Transactional
    @PostMapping
    public Amizade createAmizade(@RequestBody Amizade amizade) {
        return amizadeService.createAmizade(amizade);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarAmizade(@PathVariable Long id) {  amizadeService.deletarAmizade(id);
    }
    
}

