package com.tcg.lista.domain.services;

import com.tcg.lista.application.dto.AmizadeReadDTO;
import com.tcg.lista.application.dto.AmizadeSaveDTO;
import com.tcg.lista.domain.amizade.Amizade;
import com.tcg.lista.domain.amizade.AmizadeStatus;
import com.tcg.lista.domain.usuario.Usuario;
import com.tcg.lista.infraestructure.mysql.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public List<AmizadeReadDTO> getAmizadesByUserId(Long id) {
        return amizadeRepository.findAmizadeByUsuarioId(id).get().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public AmizadeReadDTO getAmizade(Long id) {
        return toDTO(amizadeRepository.findById(id).orElse(null));
    }

    public AmizadeReadDTO createAmizade(AmizadeSaveDTO amizadeDTO) {

        var amizade = toEntity(amizadeDTO);

        amizade.setDataInicio(LocalDate.now());
        amizade.setStatus(AmizadeStatus.ATIVO.getValue());

        return toDTO(amizadeRepository.save(amizade));
    }

    public AmizadeReadDTO updateAmizade(Long id, AmizadeSaveDTO amizadeDTO) {

        var amizade = amizadeRepository.getReferenceById(id);

        amizade.setUsuario(new Usuario(amizadeDTO.usuarioId()));
        amizade.setAmigo(new Usuario(amizadeDTO.amigoId()));
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
        amizade.setUsuario(new Usuario(amizadeDTO.usuarioId()));
        amizade.setAmigo(new Usuario(amizadeDTO.amigoId()));

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
