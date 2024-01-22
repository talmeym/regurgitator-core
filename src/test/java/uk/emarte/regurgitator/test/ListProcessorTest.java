/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.ListProcessor;
import uk.emarte.regurgitator.core.Message;
import uk.emarte.regurgitator.core.RegurgitatorException;
import uk.emarte.regurgitator.core.ValueProcessor;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class ListProcessorTest {
    @Test
    public void testProcessor() throws RegurgitatorException {
        ListProcessor listProcessor = new ListProcessor(asList(processor(), processor()));
        List<Integer> input = asList(2, 4, 6);
        assertEquals(asList(4, 6, 8), listProcessor.process(input, null));
        assertEquals(asList(2, 4, 6), input); // double check input not changed
    }

    @Test
    public void testNotList() throws RegurgitatorException {
        ListProcessor listProcessor = new ListProcessor(asList(processor(), processor()));
        assertEquals(singletonList(6), listProcessor.process(4, null));
    }

    private ValueProcessor processor() {
        return new IncrementValueProcessor();
    }

    private static class IncrementValueProcessor implements ValueProcessor {
        @Override
        public Object process(Object value, Message message) {
            return ((Integer)value) + 1;
        }
    }
}