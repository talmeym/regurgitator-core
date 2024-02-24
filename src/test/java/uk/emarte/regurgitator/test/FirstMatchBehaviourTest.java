package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.FirstMatchBehaviour;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstMatchBehaviourTest {
    private static final FirstMatchBehaviour toTest = new FirstMatchBehaviour();

    @Test
    public void testHappyPath() {
        List<Object> evaluated = asList("2", "3");
        List<Object> all = asList("1", "2", "3", "4");
        List<Object> list = toTest.evaluate(evaluated, all);
        assertEquals(singletonList("2"), list);
    }

    @Test
    public void testNoMatchesNoDefault() {
        List<Object> all = asList("1", "2", "3", "4");
        List<Object> list = toTest.evaluate(new ArrayList<>(), all);
        assertTrue(list.isEmpty());
    }
}