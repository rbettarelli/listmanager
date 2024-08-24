package com.rbettarelli.listmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbettarelli.listmanager.model.Item;
import com.rbettarelli.listmanager.repository.ItemRepository;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
