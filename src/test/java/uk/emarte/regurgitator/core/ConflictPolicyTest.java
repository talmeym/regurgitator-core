/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static uk.emarte.regurgitator.core.ConflictPolicy.*;

public class ConflictPolicyTest {

    @Test
    public void testConflictPolicy() {
        Assert.assertEquals("existing", LEAVE.resolveConflict("existing", "new", CoreTypes.STRING));
        Assert.assertEquals("new", REPLACE.resolveConflict("existing", "new", CoreTypes.STRING));
        Assert.assertEquals("existingnew", CONCAT.resolveConflict("existing", "new", CoreTypes.STRING));
        Assert.assertEquals("exing", REMOVE.resolveConflict("existing", "ist", CoreTypes.STRING));

        Assert.assertEquals(new ArrayList<>(), REMOVE.resolveConflict(asList("1", "2", "3", "3"), asList("1", "2", "3"), CoreTypes.LIST_OF_STRING));
        Assert.assertEquals(new ArrayList<>(), REMOVE.resolveConflict(asList(1, 2, 3, 3), asList(1, 2, 3), CoreTypes.LIST_OF_NUMBER));
    }
}
