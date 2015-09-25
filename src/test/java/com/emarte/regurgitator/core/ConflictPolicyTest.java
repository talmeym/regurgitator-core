package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.ArrayList;

import static com.emarte.regurgitator.core.ConflictPolicy.*;
import static com.emarte.regurgitator.core.CoreTypes.*;
import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;

public class ConflictPolicyTest {

	@Test
	public void testThis() {
		assertEquals("existing", LEAVE.resolveConflict("existing", "new", STRING));
		assertEquals("new", REPLACE.resolveConflict("existing", "new", STRING));
		assertEquals("existingnew", CONCAT.resolveConflict("existing", "new", STRING));
		assertEquals("exing", REMOVE.resolveConflict("existing", "ist", STRING));

		assertEquals(new ArrayList(), REMOVE.resolveConflict(asList("1", "2", "3", "3"), asList("1", "2", "3"), LIST_OF_STRING));
		assertEquals(new ArrayList(), REMOVE.resolveConflict(asList(1, 2, 3, 3), asList(1, 2, 3), LIST_OF_NUMBER));
	}
}
