package com.rbettarelli.listmanager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String state;
    private boolean priority;

    @ManyToOne
    @JoinColumn(name = "task_list_id")
    @JsonIgnore
    
    private TaskList taskList;

    @Transient
    @JsonProperty("task_list_id")
   
    private Long taskListId;

    public Item() {
    }

    public Item(Long id, String title, String state, boolean priority, TaskList taskList) {
        this.id = id;
        this.title = title;
        this.state = state;
        this.priority = priority;
        this.taskList = taskList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", priority=" + priority +
                ", taskListId=" + (taskList != null ? taskList.getId() : null) +
                '}';
    }

    public Long getTaskListId() {
        return taskList != null ? taskList.getId() : taskListId;
    }

    public void setTaskListId(Long taskListId) {
        this.taskListId = taskListId;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

}