package com.rbettarelli.listmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbettarelli.listmanager.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
