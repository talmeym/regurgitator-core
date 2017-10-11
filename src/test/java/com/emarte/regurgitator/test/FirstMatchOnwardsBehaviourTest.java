/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.FirstMatchOnwardsBehaviour;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;


public class FirstMatchOnwardsBehaviourTest {
    private static final FirstMatchOnwardsBehaviour toTest = new FirstMatchOnwardsBehaviour();

    @Test
    public void testHappyPath() throws Exception {
        List<Object> evaluated = asList("2", (Object) "3");
        List<Object> all = asList("1", "2", "3", (Object) "4");
        List list = toTest.evaluate(evaluated, all, "4");
        assertEquals(asList("2", "3", "4"), list);
    }

    @Test
    public void testDefault() throws Exception {
        List<Object> all = asList("1", "2", "3", (Object) "4");
        List<Object> list = toTest.evaluate(new ArrayList<Object>(), all, "3");
        assertEquals(asList("3", "4"), list);
    }

    @Test(expected = IllegalStateException.class)
    public void testNoDefault() throws Exception {
        List<Object> all = asList("1", "2", (Object) "3");
        toTest.evaluate(new ArrayList<Object>(), all, null);
    }
}