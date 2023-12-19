/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.AllMatchesBehaviour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class AllMatchesBehaviourTest {
    private static final AllMatchesBehaviour toTest = new AllMatchesBehaviour();

    @Test
    public void testHappyPath() {
        List<Object> evaluated = asList("1", "3");
        List<Object> all = asList("1", "2", "3");
        List<Object> list = toTest.evaluate(evaluated, all, "4");
        assertEquals(asList("1", "3"), list);
    }

    @Test
    public void testDefault() {
        List<Object> all = asList("1", "2", "3");
        List<Object> list = toTest.evaluate(new ArrayList<>(), all, "4");
        assertEquals(singletonList("4"), list);
    }

    @Test(expected = IllegalStateException.class)
    public void testNoDefault() {
        List<Object> all = asList("1", "2", "3");
        toTest.evaluate(new ArrayList<>(), all, null);
    }
}
