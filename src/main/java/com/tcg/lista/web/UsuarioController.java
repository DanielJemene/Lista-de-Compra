package com.tcg.lista.web;

import com.tcg.lista.application.UsuarioService;
import com.tcg.lista.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public Usuario getUsuario(@PathVariable UUID id) {
        return usuarioService.getUsuario(id);
    }

    @Transactional
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable UUID id) {  usuarioService.deletarUsuario(id); }

}

