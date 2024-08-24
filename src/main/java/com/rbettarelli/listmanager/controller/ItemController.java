package com.rbettarelli.listmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rbettarelli.listmanager.model.Item;
import com.rbettarelli.listmanager.model.TaskList;
import com.rbettarelli.listmanager.repository.TaskListRepository;
import com.rbettarelli.listmanager.service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private TaskListRepository taskListRepository;

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        System.out.println("Item recebido: " + item); // Log para verificar os dados recebidos

        if (item.getTaskListId() == null) {
            throw new IllegalArgumentException("Task list ID is required");
        }

        System.out.println("TaskListId recebido: " + item.getTaskListId()); // Log para verificar o taskListId

        TaskList taskList = taskListRepository.findById(item.getTaskListId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid task list ID"));

        item.setTaskList(taskList);
        return itemService.save(item);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.findById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.findAll();
        return ResponseEntity.ok(items);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item itemDetails) {
        Optional<Item> itemOptional = itemService.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            item.setTitle(itemDetails.getTitle());
            item.setState(itemDetails.getState());
            item.setPriority(itemDetails.isPriority());
            Item updatedItem = itemService.save(item);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/state")
    public ResponseEntity<Item> updateItemState(@PathVariable Long id, @RequestParam String state) {
        Optional<Item> itemOptional = itemService.findById(id);
        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            item.setState(state);
            Item updatedItem = itemService.save(item);
            return ResponseEntity.ok(updatedItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}