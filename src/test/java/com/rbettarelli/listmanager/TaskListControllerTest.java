package com.rbettarelli.listmanager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.rbettarelli.listmanager.model.TaskList;
import com.rbettarelli.listmanager.service.TaskListService;

public class TaskListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskListService taskListService;

    @Test
    void testGetAllTaskLists() throws Exception {
        TaskList taskList = new TaskList();
        taskList.setId(1L);
        taskList.setName("Test List");

        when(taskListService.findAll()).thenReturn(Collections.singletonList(taskList));

        mockMvc.perform(get("/api/tasklists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test List"));
    }

    @Test
    void testCreateTaskList() throws Exception {
        TaskList taskList = new TaskList();
        taskList.setName("New List");

        when(taskListService.save(any(TaskList.class))).thenReturn(taskList);

        mockMvc.perform(post("/api/tasklists")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"New List\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New List"));
    }
}
