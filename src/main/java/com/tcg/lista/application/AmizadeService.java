package com.tcg.lista.application;

import com.tcg.lista.domain.usuario.Amizade;
import com.tcg.lista.domain.usuario.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
