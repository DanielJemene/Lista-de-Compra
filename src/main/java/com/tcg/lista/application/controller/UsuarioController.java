package com.tcg.lista.application.controller;

import com.tcg.lista.application.dto.UsuarioReadDTO;
import com.tcg.lista.application.dto.UsuarioSaveDTO;
import com.tcg.lista.domain.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioReadDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioReadDTO getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuarioDTO(id);
    }

    @Transactional
    @PostMapping
    public UsuarioReadDTO createUsuario(@Valid @RequestBody UsuarioSaveDTO usuarioDTO) {
        return usuarioService.createUsuario(usuarioDTO);
    }

    @PutMapping("/{id}")
    public UsuarioReadDTO updateUsuario(@PathVariable Long id, @RequestBody UsuarioSaveDTO usuarioDTO) {
        return usuarioService.update(id, usuarioDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {  usuarioService.deletarUsuario(id); }

}

