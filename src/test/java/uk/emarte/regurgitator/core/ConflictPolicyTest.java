/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class ConflictPolicyTest {

    @Test
    public void testThis() {
        Assert.assertEquals("existing", ConflictPolicy.LEAVE.resolveConflict("existing", "new", CoreTypes.STRING));
        Assert.assertEquals("new", ConflictPolicy.REPLACE.resolveConflict("existing", "new", CoreTypes.STRING));
        Assert.assertEquals("existingnew", ConflictPolicy.CONCAT.resolveConflict("existing", "new", CoreTypes.STRING));
        Assert.assertEquals("exing", ConflictPolicy.REMOVE.resolveConflict("existing", "ist", CoreTypes.STRING));

        Assert.assertEquals(new ArrayList(), ConflictPolicy.REMOVE.resolveConflict(asList("1", "2", "3", "3"), asList("1", "2", "3"), CoreTypes.LIST_OF_STRING));
        Assert.assertEquals(new ArrayList(), ConflictPolicy.REMOVE.resolveConflict(asList(1, 2, 3, 3), asList(1, 2, 3), CoreTypes.LIST_OF_NUMBER));
    }
}
