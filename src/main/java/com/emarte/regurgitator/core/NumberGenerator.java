package com.emarte.regurgitator.core;

import java.util.Random;

public class NumberGenerator implements ValueGenerator {
	private static Random RANDOM = new Random();

	@Override
	public Object generate() throws RegurgitatorException {
		return RANDOM.nextLong();
	}
}
