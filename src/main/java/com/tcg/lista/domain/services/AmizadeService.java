package com.tcg.lista.domain.services;

import com.tcg.lista.domain.usuario.Amizade;
import com.tcg.lista.infraestructure.mysql.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public List<Amizade> getAllAmizades() {
        return amizadeRepository.findAll();
    }

    public Amizade getAmizade(Long id) {
        return amizadeRepository.findById(id).orElse(null);
    }

    public Amizade createAmizade(Amizade amizade) {
        return amizadeRepository.save(amizade);
    }

    public void deletarAmizade(Long id)  { amizadeRepository.deleteById(id); }
}
