package com.tcg.lista.domain.service;

import com.tcg.lista.application.dto.AmizadeReadDTO;
import com.tcg.lista.application.dto.AmizadeSaveDTO;
import com.tcg.lista.application.exception.EntityNotFoundException;
import com.tcg.lista.domain.entity.amizade.Amizade;
import com.tcg.lista.domain.entity.amizade.AmizadeStatus;
import com.tcg.lista.infraestructure.mysql.repository.AmizadeRepository;
import com.tcg.lista.infraestructure.mysql.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<AmizadeReadDTO> getAmizadesByUserId(Long id) {
        return amizadeRepository.findAmizadeByUsuarioId(id).get()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AmizadeReadDTO getAmizadeDTO(Long id) {
        return toDTO(getAmizade(id));
    }

    public Amizade getAmizade(Long id) {
        return amizadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Amizade"));
    }

    public AmizadeReadDTO createAmizade(AmizadeSaveDTO amizadeDTO) {

        var amizade = toEntity(amizadeDTO);

        amizade.setDataInicio(LocalDate.now());
        amizade.setStatus(AmizadeStatus.ATIVO.getValue());

        return toDTO(amizadeRepository.save(amizade));
    }

    public AmizadeReadDTO updateAmizade(Long id, AmizadeSaveDTO amizadeDTO) {

        var amizade = getAmizade(id);

        amizade.setUsuario(usuarioRepository.findById(amizadeDTO.amigoId()).orElseThrow(() -> new EntityNotFoundException("Amigo")));
        amizade.setAmigo(usuarioRepository.findById(amizadeDTO.amigoId()).orElseThrow(() -> new EntityNotFoundException("Amigo")));
        amizade.setStatus(amizadeDTO.status().getValue());

        if (AmizadeStatus.INATIVO.getValue() == amizade.getStatus()
            && amizade.getDataFim() == null) {
            amizade.setDataFim(LocalDate.now());
        }

        return toDTO(amizadeRepository.save(amizade));
    }

    public void deletarAmizade(Long id)  { amizadeRepository.deleteById(id); }

    public Amizade toEntity(AmizadeSaveDTO amizadeDTO){

        var amizade = new Amizade();

        amizade.setUsuario(usuarioRepository.findById(amizadeDTO.usuarioId()).orElseThrow(() -> new EntityNotFoundException("Usuário")));
        amizade.setAmigo(usuarioRepository.findById(amizadeDTO.amigoId()).orElseThrow(() -> new EntityNotFoundException("Amigo")));

        if (amizadeDTO.status() != null) {
            amizade.setStatus(amizadeDTO.status().getValue());
        }

        return amizade;
    }

    public AmizadeReadDTO toDTO(Amizade amizade){

        return new AmizadeReadDTO(
                amizade.getId(),
                amizade.getUsuario().getId(),
                amizade.getAmigo().getId(),
                amizade.getDataInicio(),
                amizade.getDataFim(),
                AmizadeStatus.fromValue(amizade.getStatus())
        );

    }
}
