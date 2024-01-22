/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.SubstituteProcessor;

import static org.junit.Assert.assertEquals;

public class SubstituteProcessorTest {

    @Test
    public void testProcessor() {
        SubstituteProcessor toTest = new SubstituteProcessor("token", "nekot");
        assertEquals("1234nekot1234nekot1234nekot1234", toTest.process("1234token1234token1234token1234", null));
    }
}
