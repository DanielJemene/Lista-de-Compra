package com.tcg.lista.application.controller;

import com.tcg.lista.domain.services.UsuarioService;
import com.tcg.lista.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioService.getUsuario(id);
    }

    @Transactional
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {  usuarioService.deletarUsuario(id); }

}

