package com.tcg.lista.application.controller;

import com.tcg.lista.application.dto.AmizadeReadDTO;
import com.tcg.lista.application.dto.AmizadeSaveDTO;
import com.tcg.lista.domain.service.AmizadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amizades")
public class AmizadeController {

    @Autowired
    private AmizadeService amizadeService;

    @GetMapping("/byuser/{id}")
    public List<AmizadeReadDTO> getAmizadesByUserId(@PathVariable Long id) {
        return amizadeService.getAmizadesByUserId(id);
    }

    @GetMapping("/{id}")
    public AmizadeReadDTO getAmizade(@PathVariable Long id) {
        return amizadeService.getAmizadeDTO(id);
    }

    @Transactional
    @PostMapping
    public AmizadeReadDTO createAmizade(@RequestBody @Valid AmizadeSaveDTO amizadeDTO) {
        return amizadeService.createAmizade(amizadeDTO);
    }

    @Transactional
    @PutMapping("/{id}")
    public AmizadeReadDTO updateAmizade(@PathVariable Long id, @RequestBody @Valid AmizadeSaveDTO amizadeDTO) {
        return amizadeService.updateAmizade(id, amizadeDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarAmizade(@PathVariable Long id) {  amizadeService.deletarAmizade(id);
    }
    
}

