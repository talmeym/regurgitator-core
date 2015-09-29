package com.emarte.regurgitator.core;

import java.util.Random;

public class NumberGenerator implements ValueGenerator {
	private static Random RANDOM = new Random();

	private final Integer max;

	public NumberGenerator(int max) {
		this.max = max;
	}

	public NumberGenerator() {
		max = null;
	}

	@Override
	public Object generate() throws RegurgitatorException {
		return max != null ? (long) RANDOM.nextInt(max) : RANDOM.nextLong();
	}
}
