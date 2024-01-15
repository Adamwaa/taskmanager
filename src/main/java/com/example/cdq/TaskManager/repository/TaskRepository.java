package com.example.cdq.TaskManager.repository;

import com.example.cdq.TaskManager.model.Task;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaskRepository {
    private final Map<String, Task> tasks = new ConcurrentHashMap<>();

    public void saveTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public Task getTask(String taskId) {
        return tasks.get(taskId);
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public Map<String, Task> getAllTasks() {
        return new ConcurrentHashMap<>(tasks);
    }
}