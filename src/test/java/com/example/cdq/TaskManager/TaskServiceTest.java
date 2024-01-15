package com.example.cdq.TaskManager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.cdq.TaskManager.model.Task;
import com.example.cdq.TaskManager.repository.TaskRepository;
import com.example.cdq.TaskManager.service.TaskService;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

@SpringBootTest
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @MockBean
    AsyncTaskProcessor asyncTaskProcessor;

    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService(taskRepository, asyncTaskProcessor);
    }

    @Test
    void whenCreateTask_thenTaskIsSaved() {
        Task task = new Task();
        doNothing().when(taskRepository).saveTask(any(Task.class));
        Task createdTask = taskService.createTask(task.getPattern(), task.getInput());
        assertNotNull(createdTask);
        assertEquals(task.getPattern(), createdTask.getPattern());
        assertEquals(task.getInput(), createdTask.getInput());
    }


    @Test
    void whenGetTask_thenTaskIsReturned() {
        Task task = new Task();
        task.setId("1");
        when(taskRepository.getTask("1")).thenReturn(task);
        Task foundTask = taskService.getTask("1");
        assertNotNull(foundTask);
        assertEquals("1", foundTask.getId());
    }
}
