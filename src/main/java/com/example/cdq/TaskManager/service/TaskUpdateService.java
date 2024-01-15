package com.example.cdq.TaskManager.service;

import com.example.cdq.TaskManager.model.Task;
import com.example.cdq.TaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskUpdateService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void updateTaskProgress(Task task, int progress) {
        task.setCompletionProgress(progress);
        taskRepository.updateTask(task);
    }

    public void completeTask(Task task, int position, int typos) {
        //task.setMatchResult(matchResult);
        task.setPosition(position);
        task.setTypos(typos);
        task.setProcessingStatus("COMPLETED");
        task.setCompletionProgress(100);
        taskRepository.saveTask(task);
    }


}
