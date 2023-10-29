package com.tcg.lista.infraestructure.mysql.repository;

import com.tcg.lista.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}


