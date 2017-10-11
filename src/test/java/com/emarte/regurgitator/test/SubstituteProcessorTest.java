/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.SubstituteProcessor;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SubstituteProcessorTest {

    @Test
    public void testThis() {
        SubstituteProcessor toTest = new SubstituteProcessor("token", "nekot");
        assertEquals("1234nekot1234nekot1234nekot1234", toTest.process("1234token1234token1234token1234", null));
    }
}
