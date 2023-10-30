package com.tcg.lista.domain.services;

import com.tcg.lista.application.dto.UsuarioReadDTO;
import com.tcg.lista.application.dto.UsuarioSaveDTO;
import com.tcg.lista.application.exception.BusinessException;
import com.tcg.lista.application.exception.EntityNotFoundException;
import com.tcg.lista.domain.enitty.usuario.Usuario;
import com.tcg.lista.domain.enitty.usuario.UsuarioStatus;
import com.tcg.lista.infraestructure.mysql.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AmizadeService amizadeService;

    public List<UsuarioReadDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Usuario getUsuario(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário"));
    }

    public UsuarioReadDTO getUsuarioDTO(Long id) {
        return toDTO(getUsuario(id));
    }

    public UsuarioReadDTO createUsuario(UsuarioSaveDTO usuarioDTO) {

        isIdadeValida(usuarioDTO.dataNascimento());

        var usuario = toEntity(usuarioDTO);
        usuario.setStatus(UsuarioStatus.ATIVO.getValue());

        return toDTO(usuarioRepository.save(usuario));
    }

    private void isIdadeValida(LocalDate dtNascimento){

        if(Period.between(dtNascimento, LocalDate.now()).getYears() < 18) {
            throw new BusinessException("Idade não permitida, Usuário menor que 18 anos.");
        }
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioReadDTO update(Long id, UsuarioSaveDTO usuarioDTO){

        var usuario = usuarioRepository.getReferenceById(id);

        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setSenha(usuarioDTO.senha());
        usuario.setStatus(usuarioDTO.status().getValue());

        return toDTO(usuarioRepository.save(usuario));
    }

    public Usuario toEntity(UsuarioSaveDTO usuarioDTO){

        var usuario = new Usuario();

        usuario.setNome(usuarioDTO.nome());
        usuario.setDataNascimento(usuarioDTO.dataNascimento());
        usuario.setEmail(usuarioDTO.email());
        usuario.setCpf(usuarioDTO.cpf());
        usuario.setSenha(usuarioDTO.senha());

        if (usuarioDTO.status() != null) {
            usuario.setStatus(usuarioDTO.status().getValue());
        }

        return usuario;
    }

    public UsuarioReadDTO toDTO(Usuario usuario) {

        return new UsuarioReadDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getEmail(),
                usuario.getCpf(),
                UsuarioStatus.fromValue(usuario.getStatus()),
                amizadeService.getAmizadesByUserId(usuario.getId())
        );
    }

}
