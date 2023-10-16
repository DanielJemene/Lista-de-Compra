package com.tcg.lista.application;

import com.tcg.lista.domain.lista.Lista;
import com.tcg.lista.domain.lista.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    @Autowired
    private ListaRepository listaRepository;

    public List<Lista> getAllListas() {
        return listaRepository.findAll();
    }

    public Lista getListaById(Long id) {
        return listaRepository.findById(id).orElse(null);
    }

    public Lista getListaByNome(String nome) {
        return listaRepository.findByNome(nome).orElse(null);
    }

    public Lista createLista(Lista lista) {
        return listaRepository.save(lista);
    }

    public Lista updateLista(Long id, Lista lista) {
        if (listaRepository.existsById(id)) {
            lista.setId(id);
            return listaRepository.save(lista);
        }
        return null;
    }

    public void deleteLista(Long id) {
        listaRepository.deleteById(id);
    }
}
