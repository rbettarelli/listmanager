package com.rbettarelli.listmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbettarelli.listmanager.model.TaskList;
import com.rbettarelli.listmanager.service.TaskListService;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {
    @Autowired
    private TaskListService taskListService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TaskList> createTaskList(@RequestBody TaskList taskList) {
        TaskList savedTaskList = taskListService.save(taskList);
        return ResponseEntity.ok(savedTaskList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getTaskListById(@PathVariable Long id) {
        Optional<TaskList> taskList = taskListService.findById(id);
        return taskList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TaskList>> getAllTaskLists() {
        List<TaskList> taskLists = taskListService.findAll();
        return ResponseEntity.ok(taskLists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> updateTaskList(@PathVariable Long id, @RequestBody TaskList taskListDetails) {
        Optional<TaskList> taskListOptional = taskListService.findById(id);
        if (taskListOptional.isPresent()) {
            TaskList taskList = taskListOptional.get();
            taskList.setName(taskListDetails.getName());
            TaskList updatedTaskList = taskListService.save(taskList);
            return ResponseEntity.ok(updatedTaskList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id) {
        taskListService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}