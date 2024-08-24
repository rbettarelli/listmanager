package com.rbettarelli.listmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbettarelli.listmanager.model.TaskList;
import com.rbettarelli.listmanager.repository.TaskListRepository;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    public TaskList save(TaskList taskList) {
        taskList.getItems().forEach(item -> item.setTaskList(taskList));
        return taskListRepository.save(taskList);
    }

    public Optional<TaskList> findById(Long id) {
        return taskListRepository.findById(id);
    }

    public List<TaskList> findAll() {
        return taskListRepository.findAll();
    }

    public void deleteById(Long id) {
        taskListRepository.deleteById(id);
    }
}