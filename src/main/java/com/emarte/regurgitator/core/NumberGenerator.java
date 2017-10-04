package com.emarte.regurgitator.core;

import java.util.Random;

final class NumberGenerator implements ValueGenerator {
	private static Random RANDOM = new Random();

	private final Integer max;

	NumberGenerator(Integer max) {
		this.max = max;
	}

	NumberGenerator() {
		max = null;
	}

	@Override
	public Object generate() throws RegurgitatorException {
		return max != null ? (long) RANDOM.nextInt(max) : RANDOM.nextLong();
	}
}
