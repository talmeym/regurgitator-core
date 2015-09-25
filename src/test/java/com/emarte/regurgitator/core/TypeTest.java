package com.emarte.regurgitator.core;

import java.util.*;

public abstract class TypeTest {
	protected Set set(Object... things) {
		return new LinkedHashSet(Arrays.asList(things));
	}

	protected List list(Object... things) {
		return Arrays.asList(things);
	}
}
