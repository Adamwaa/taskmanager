package com.example.cdq.TaskManager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private String id;
    private String pattern;
    private String processingStatus;
    private String input;
    private int position;
    private int typos;
    private int completionProgress;


}
