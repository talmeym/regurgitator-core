package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.NumberGenerator;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NumberGeneratorTest {
    private static final Integer MAX = 100;

    @Test
    public void testNoMax() {
        NumberGenerator toTest = new NumberGenerator();
        Object number = toTest.generate();
        assertTrue(number instanceof Long);
    }

    @Test
    public void testMax() {
        NumberGenerator toTest = new NumberGenerator(MAX);
        Object number = toTest.generate();
        assertTrue(number instanceof Long);
        assertTrue((Long) number <= MAX);
    }
}