package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.enitty.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<List<Item>> findItemByListaId(@Param("lista_id") Long id);
}


