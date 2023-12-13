/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static org.junit.Assert.assertEquals;

public class CreateResponseTest {
    private static final String SOURCE_NAME = "name";
    private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
    private static final String PARAM_CONTEXT = "context";
    private static final String SOURCE_VALUE = "value";
    private static final String PROCESSED_VALUE = "processedValue";
    private static final ContextLocation SOURCE = new ContextLocation("context:name");
    private static final String STATIC_VALUE = "staticValue";
    private static final String SOURCE_ID = "sourceId";
    private static final String STATIC_ID = "staticId";
    private static final String SOURCE_AND_STATIC_ID = "sourceAndStatic";
    private static final String PROCESSED_ID = "processedId";

    private final ParameterPrototype sourcePrototype = new ParameterPrototype(SOURCE_NAME, STRING, PARAM_CONFLICT_POL);

    private final CreateResponse sourceToTest = new CreateResponse(SOURCE_ID, new ValueSource(SOURCE, null), new ArrayList<>());
    private final CreateResponse staticToTest = new CreateResponse(STATIC_ID, new ValueSource(null, STATIC_VALUE), new ArrayList<>());
    private final CreateResponse sourceAndStaticToTest = new CreateResponse(SOURCE_AND_STATIC_ID, new ValueSource(SOURCE, STATIC_VALUE), new ArrayList<>());

    private final ValueProcessor processor = (value, message) -> PROCESSED_VALUE;

    private final CreateResponse processorToTest = new CreateResponse(PROCESSED_ID, new ValueSource(null, STATIC_VALUE), Collections.singletonList(processor));

    private final CollectingResponseCallBack callback = new CollectingResponseCallBack();

    @Test
    public void testSource() throws RegurgitatorException {
        assertEquals(SOURCE_ID, sourceToTest.getId());
        Message message = new Message(callback);
        Parameters contextParameters = message.getContext(PARAM_CONTEXT);
        contextParameters.setValue(new Parameter(sourcePrototype, SOURCE_VALUE));
        assertEquals(1, contextParameters.size());
        sourceToTest.execute(message);
        assertEquals(SOURCE_VALUE, callback.getValue());
    }

    @Test
    public void testStatic() throws RegurgitatorException {
        assertEquals(STATIC_ID, staticToTest.getId());
        Message message = new Message(callback);
        Parameters contextParameters = message.getContext(PARAM_CONTEXT);
        assertEquals(0, contextParameters.size());
        staticToTest.execute(message);
        assertEquals(STATIC_VALUE, callback.getValue());
    }

    @Test
    public void testSourceAndStatic_sourceFound() throws RegurgitatorException {
        assertEquals(SOURCE_AND_STATIC_ID, sourceAndStaticToTest.getId());
        Message message = new Message(callback);
        Parameters contextParameters = message.getContext(PARAM_CONTEXT);
        contextParameters.setValue(new Parameter(sourcePrototype, SOURCE_VALUE));
        assertEquals(1, contextParameters.size());
        sourceAndStaticToTest.execute(message);
        assertEquals(SOURCE_VALUE, callback.getValue());
    }

    @Test
    public void testSourceAndStatic_sourceNotFound() throws RegurgitatorException {
        assertEquals(SOURCE_AND_STATIC_ID, sourceAndStaticToTest.getId());
        Message message = new Message(callback);
        Parameters contextParameters = message.getContext(PARAM_CONTEXT);
        assertEquals(0, contextParameters.size());
        sourceAndStaticToTest.execute(message);
        assertEquals(STATIC_VALUE, callback.getValue());
    }

    @Test
    public void testProcessor() throws RegurgitatorException {
        assertEquals(PROCESSED_ID, processorToTest.getId());
        Message message = new Message(callback);
        Parameters contextParameters = message.getContext(PARAM_CONTEXT);
        assertEquals(0, contextParameters.size());
        processorToTest.execute(message);
        assertEquals(PROCESSED_VALUE, callback.getValue());
    }

    private static class CollectingResponseCallBack implements ResponseCallBack {
        private Object value;

        @Override
        public void respond(Message message, Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }
    }
}
