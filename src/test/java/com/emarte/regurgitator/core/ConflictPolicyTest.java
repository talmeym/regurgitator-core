package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.*;
import static com.emarte.regurgitator.core.CoreTypes.STRING;

public class ConflictPolicyTest {

	@Test
	public void testThis() {
		LEAVE.resolveConflict("existing", "new", STRING).equals("exisitng");
		REPLACE.resolveConflict("existing", "new", STRING).equals("new");
		CONCAT.resolveConflict("existing", "new", STRING).equals("existingnew");
		REMOVE.resolveConflict("existing", "ist", STRING).equals("exing");
	}
}
