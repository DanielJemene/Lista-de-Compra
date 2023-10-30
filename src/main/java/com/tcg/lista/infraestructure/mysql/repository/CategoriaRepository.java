package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.enitty.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
