package com.emarte.regurgitator.core;

import java.util.Random;

public interface ValueGenerator {
	public Object generate() throws RegurgitatorException;

	public enum DefaultImpl implements ValueGenerator {
		UUID {
			@Override
			public Object generate() throws RegurgitatorException {
				return UUID.generate();
			}
		},

		NUMBER {
			@Override
			public Object generate() throws RegurgitatorException {
				return RANDOM.nextLong();
			}
		};

		private static Random RANDOM = new Random();

		static boolean contains(String name) {
			for(DefaultImpl value: values()) {
				if(value.name().equals(name)) {
					return true;
				}
			}

			return false;
		}
	}
}
