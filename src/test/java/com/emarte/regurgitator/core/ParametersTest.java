/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.*;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static org.junit.Assert.assertEquals;

public class ParametersTest {

    private static final String NAME = "name";
    private static final String NEW_NAME = "newName";

    @Test
    public void testThis() {
        Parameters parameters = new Parameters("parameters");

        ParameterPrototype leavePrototype = new ParameterPrototype(NAME, STRING, LEAVE);

        // set gives parameter a value

        parameters.setValue(new Parameter(leavePrototype, "value"));

        assertEquals(1, parameters.size());
        assertEquals("value", parameters.getValue(NAME));

        // second 'set' with leave prototype - no change to value

        parameters.setValue(new Parameter(leavePrototype, "newvalue"));

        assertEquals(1, parameters.size());
        assertEquals("value", parameters.getValue(NAME));

        ParameterPrototype replacePrototype = new ParameterPrototype(NAME, STRING, REPLACE);

        // set with REPLACE gives param new value

        parameters.setValue(new Parameter(replacePrototype, "newvalue"));

        assertEquals(1, parameters.size());
        assertEquals("newvalue", parameters.getValue(NAME));

        ParameterPrototype concatPrototype = new ParameterPrototype(NAME, STRING, CONCAT);

        // concat adds the values together

        parameters.setValue(new Parameter(concatPrototype, "two"));

        assertEquals(1, parameters.size());
        assertEquals("newvaluetwo", parameters.getValue(NAME));

        ParameterPrototype removePrototype = new ParameterPrototype(NAME, STRING, REMOVE);

        // remove takes one value from another

        parameters.setValue(new Parameter(removePrototype, "two"));

        assertEquals(1, parameters.size());
        assertEquals("newvalue", parameters.getValue(NAME));

        ParameterPrototype newPrototype = new ParameterPrototype(NEW_NAME, STRING, LEAVE);

        // second parameter is added, not merged

        parameters.setValue(new Parameter(newPrototype, "value"));

        assertEquals(2, parameters.size());
        assertEquals("newvalue", parameters.getValue(NAME));
        assertEquals("value", parameters.getValue(NEW_NAME));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValueType() {
        Parameters parameters = new Parameters("parameters");

        ParameterPrototype prototype = new ParameterPrototype("name", STRING, REPLACE);

        parameters.setValue(new Parameter(prototype, 125L)); // NUMBER, not STRING
    }
}
