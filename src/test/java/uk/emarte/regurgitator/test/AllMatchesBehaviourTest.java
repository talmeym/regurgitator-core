/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.AllMatchesBehaviour;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AllMatchesBehaviourTest {
    private static final AllMatchesBehaviour toTest = new AllMatchesBehaviour();

    @Test
    public void testHappyPath() {
        List<Object> evaluated = asList("1", "3");
        List<Object> all = asList("1", "2", "3", "4");
        List<Object> list = toTest.evaluate(evaluated, all);
        assertEquals(asList("1", "3"), list);
    }

    @Test
    public void testNoMatchesNoDefault() {
        List<Object> all = asList("1", "2", "3", "4");
        List<Object> list = toTest.evaluate(new ArrayList<>(), all);
        assertTrue(list.isEmpty());
    }
}
