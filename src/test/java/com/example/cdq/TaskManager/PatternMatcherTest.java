package com.example.cdq.TaskManager;

import com.example.cdq.TaskManager.PatternLogic.PatternMatcher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternMatcherTest {

    private final PatternMatcher patternMatcher = new PatternMatcher();

    @Test
    public void testFindPatternPosition() {
        assertEquals(0, patternMatcher.findPatternPosition("abc", "abcdef"), "Pattern found at start");
        assertEquals(2, patternMatcher.findPatternPosition("cde", "abcdef"), "Pattern found in middle");
        assertEquals(0, patternMatcher.findPatternPosition("xyz", "abcdef"), "Pattern not found");
        assertEquals(3, patternMatcher.findPatternPosition("de", "abcdef"), "Pattern with typos");
        assertEquals(0, patternMatcher.findPatternPosition("", "abcdef"), "Empty pattern");
        assertEquals(-1, patternMatcher.findPatternPosition("abcdefg", "abcdef"), "Pattern longer than input");
    }

    @Test
    public void testCountTypos() {
        assertEquals(0, patternMatcher.countTypos("abc", "abcdef", 0), "No typos");
        assertEquals(1, patternMatcher.countTypos("abc", "adcdef", 0), "One typo");
        assertEquals(0, patternMatcher.countTypos("", "abcdef", 0), "Empty pattern");
        assertEquals(0, patternMatcher.countTypos("abc", "defabc", 3), "Multiple typos");
    }
}
