package com.example.cdq.TaskManager;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import com.example.cdq.TaskManager.PatternLogic.PatternMatcher;
import com.example.cdq.TaskManager.model.Task;
import com.example.cdq.TaskManager.service.TaskUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncTaskProcessorTest {

    @Mock
    private TaskUpdateService taskUpdateService;

    @Mock
    private PatternMatcher patternMatcher;

    @InjectMocks
    private AsyncTaskProcessor asyncTaskProcessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessTaskAsync() throws ExecutionException, InterruptedException {
        Task mockTask = mock(Task.class);
        when(mockTask.getPattern()).thenReturn("abc");
        when(mockTask.getInput()).thenReturn("abcdef");
        when(patternMatcher.findPatternPosition(anyString(), anyString())).thenReturn(0);
        when(patternMatcher.countTypos(anyString(), anyString(), anyInt())).thenReturn(0);
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> asyncTaskProcessor.processTaskAsync(mockTask));
        future.get();
        verify(patternMatcher).findPatternPosition("abc", "abcdef");
        verify(patternMatcher).countTypos("abc", "abcdef", 0);
        verify(taskUpdateService, times(10)).updateTaskProgress(eq(mockTask), anyInt());
        verify(taskUpdateService).completeTask(eq(mockTask), eq(0), eq(0));
    }

}
