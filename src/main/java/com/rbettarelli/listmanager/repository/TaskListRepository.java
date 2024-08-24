package com.rbettarelli.listmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbettarelli.listmanager.model.TaskList;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
