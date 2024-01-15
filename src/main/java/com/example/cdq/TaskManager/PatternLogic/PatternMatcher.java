package com.example.cdq.TaskManager.PatternLogic;

import org.springframework.stereotype.Service;

@Service
public class PatternMatcher {

    public int findPatternPosition(String pattern, String input) {
        int bestPos = -1;
        int leastTypos = Integer.MAX_VALUE;

        for (int i = 0; i <= input.length() - pattern.length(); i++) {
            int typos = 0;
            for (int j = 0; j < pattern.length(); j++) {
                if (input.charAt(i + j) != pattern.charAt(j)) {
                    typos++;
                }
            }
            if (typos < leastTypos) {
                leastTypos = typos;
                bestPos = i;
                if (typos == 0) break;
            }
        }
        return bestPos;
    }

    public int countTypos(String pattern, String input, int position) {
        int typos = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (input.charAt(position + i) != pattern.charAt(i)) {
                typos++;
            }
        }
        return typos;
    }
}
