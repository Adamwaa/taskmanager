package com.example.cdq.TaskManager;

import com.example.cdq.TaskManager.PatternLogic.PatternMatcher;
import com.example.cdq.TaskManager.model.Task;
import com.example.cdq.TaskManager.service.TaskUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskProcessor {

    private final TaskUpdateService taskUpdateService;

    private final PatternMatcher patternMatcher;

    @Autowired
    public AsyncTaskProcessor(TaskUpdateService taskUpdateService, PatternMatcher patternMatcher) {
        this.taskUpdateService = taskUpdateService;
        this.patternMatcher = patternMatcher;
    }

    @Async
    public void processTaskAsync(Task task) {
        String pattern = task.getPattern();
        String input = task.getInput();
        int position = patternMatcher.findPatternPosition(pattern, input);
        int typos = patternMatcher.countTypos(pattern, input, position);
        try {
            final int totalSteps = 10;
            for (int step = 1; step <= totalSteps; step++) {
                Thread.sleep(1000);
                int progress = (step * 100) / totalSteps;
                taskUpdateService.updateTaskProgress(task, progress);
            }
            taskUpdateService.completeTask(task, position, typos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
