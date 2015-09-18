package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.Arrays;

import static com.emarte.regurgitator.core.ConflictPolicy.*;
import static com.emarte.regurgitator.core.CoreTypes.LIST_OF_STRING;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static java.util.Arrays.asList;

public class ConflictPolicyTest {

	@Test
	public void testThis() {
		LEAVE.resolveConflict("existing", "new", STRING).equals("exisitng");
		REPLACE.resolveConflict("existing", "new", STRING).equals("new");
		CONCAT.resolveConflict("existing", "new", STRING).equals("existingnew");
		REMOVE.resolveConflict("existing", "ist", STRING).equals("exing");

		REMOVE.resolveConflict(asList("1", "2", "3", "3"), asList("1", "2", "3"), LIST_OF_STRING	).equals(asList("3"));
	}
}
