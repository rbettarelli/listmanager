package com.rbettarelli.listmanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rbettarelli.listmanager.model.TaskList;
import com.rbettarelli.listmanager.repository.TaskListRepository;
import com.rbettarelli.listmanager.service.TaskListService;

public class TaskListServiceTest {

   

    @Mock
    private TaskListRepository taskListRepository;

    @InjectMocks
    private TaskListService taskListService;

    public TaskListServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTaskList() {
        TaskList taskList = new TaskList();
        taskList.setName("Test List");

        when(taskListRepository.save(taskList)).thenReturn(taskList);

        TaskList savedTaskList = taskListService.save(taskList);
        assertNotNull(savedTaskList);
        assertEquals("Test List", savedTaskList.getName());
    }

    @Test
    void testFindById() {
        TaskList taskList = new TaskList();
        taskList.setId(1L);
        taskList.setName("Test List");

        when(taskListRepository.findById(1L)).thenReturn(Optional.of(taskList));

        Optional<TaskList> foundTaskList = taskListService.findById(1L);
        assertTrue(foundTaskList.isPresent());
        assertEquals("Test List", foundTaskList.get().getName());
    }

    @Test
    void testDeleteById() {
        taskListService.deleteById(1L);
        verify(taskListRepository, times(1)).deleteById(1L);
    }
    
}
