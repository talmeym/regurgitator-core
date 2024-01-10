/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.FirstMatchOnwardsBehaviour;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class FirstMatchOnwardsBehaviourTest {
    private static final FirstMatchOnwardsBehaviour toTest = new FirstMatchOnwardsBehaviour();

    @Test
    public void testHappyPath() {
        List<Object> evaluated = asList("2", "3");
        List<Object> all = asList("1", "2", "3", "4");
        List<Object> list = toTest.evaluate(evaluated, all, "4");
        assertEquals(asList("2", "3", "4"), list);
    }

    @Test
    public void testDefault() {
        List<Object> all = asList("1", "2", "3", "4");
        List<Object> list = toTest.evaluate(new ArrayList<>(), all, "3");
        assertEquals(asList("3", "4"), list);
    }

    @Test(expected = IllegalStateException.class)
    public void testNoDefault() {
        List<Object> all = asList("1", "2", "3");
        toTest.evaluate(new ArrayList<>(), all, null);
    }
}