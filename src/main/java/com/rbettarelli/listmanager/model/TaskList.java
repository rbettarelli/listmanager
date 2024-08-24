package com.rbettarelli.listmanager.model;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "taskList", orphanRemoval = true)
    @Cascade({CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.LOCK})
   
    private List<Item> items = new ArrayList<>();

    public TaskList(Long id, String name, List<Item> items) {
        this.id = id;
        this.name = name;
        this.items = items;
    }

    public TaskList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}