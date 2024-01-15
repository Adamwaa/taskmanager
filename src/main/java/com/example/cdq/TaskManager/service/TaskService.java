package com.example.cdq.TaskManager.service;

import com.example.cdq.TaskManager.AsyncTaskProcessor;
import com.example.cdq.TaskManager.repository.TaskRepository;
import com.example.cdq.TaskManager.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final AsyncTaskProcessor asyncprocessTaskAsync;

    public Task createTask(String pattern, String input) {
        String taskId = UUID.randomUUID().toString();
        Task newTask = new Task();
        newTask.setId(taskId);
        newTask.setPattern(pattern);
        newTask.setInput(input);
        newTask.setProcessingStatus("PENDING");
        newTask.setCompletionProgress(0);
        taskRepository.saveTask(newTask);
        asyncprocessTaskAsync.processTaskAsync(newTask);
        return newTask;
    }
    public Task getTask(String taskId) {
        return taskRepository.getTask(taskId);
    }

    public Collection<Task> getAllTasks() {
        return taskRepository.getAllTasks().values();
    }
}
