package com.emarte.regurgitator.core;

import java.util.UUID;

public class UuidGenerator implements ValueGenerator {

	@Override
	public Object generate() throws RegurgitatorException {
		return UUID.randomUUID();
	}
}
