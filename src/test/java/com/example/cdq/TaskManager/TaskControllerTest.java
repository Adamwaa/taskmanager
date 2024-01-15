package com.example.cdq.TaskManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import com.example.cdq.TaskManager.controller.TaskController;
import com.example.cdq.TaskManager.model.Task;
import com.example.cdq.TaskManager.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void whenGetAllTasks_thenReturnsTaskList() throws Exception {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("id1", "pattern1", "NotProcessed", "input1", -1, 0, 0));
        tasks.add(new Task("id2", "pattern2", "NotProcessed", "input2", -1, 0, 0));
        given(taskService.getAllTasks()).willReturn(tasks);
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(tasks.size()));
    }

    @Test
    void whenGetTaskById_thenReturnsTask() throws Exception {
        Task task = new Task("id1", "pattern1", "NotProcessed", "input1", -1, 0, 0);
        task.setId("1");
        given(taskService.getTask("1")).willReturn(task);
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void whenGetTaskById_thenReturnsNotFound() throws Exception {
        given(taskService.getTask("1")).willReturn(null);
        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isNotFound());
    }
}
