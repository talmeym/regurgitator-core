package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.*;

import static org.junit.Assert.assertNull;

public class CollectionProcessorTest {
    public static final Object NULL = null;

    @Test(expected = RegurgitatorException.class)
    public void testNotACollection() throws RegurgitatorException {
        CollectionProcessor processor = new AtIndexProcessor(new ValueSource(null, "here"));
        processor.process("NOT A COLLECTION", new Message(null));
    }

    @Test
    public void testPassThrough() throws RegurgitatorException {
        CollectionProcessor processor = new AtIndexProcessor(new ValueSource(null, "here"));
        assertNull(processor.process(NULL, new Message(null)));
    }
}