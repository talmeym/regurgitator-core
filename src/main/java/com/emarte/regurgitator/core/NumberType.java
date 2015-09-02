package com.emarte.regurgitator.core;

import java.util.Collection;

public class NumberType extends AbstractSingleType<Long> {
	private static DecimalType DECIMAL = new DecimalType();

	@Override
	public Long createNew() {
		return 0l;
	}

	@Override
	public Long concat(Long prefix, Long suffix) {
		return prefix + suffix;
	}

	@Override
	public Long remove(Long existingValue, Long newValue) {
		return existingValue - newValue;
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof Long;
	}

	@Override
	public Long convert(Object value) {
		if (validate(value)) {
			return (Long) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value, this);
		}

		if (DECIMAL.validate(value)) {
			return Math.round((Double) value);
		}

		return Long.parseLong(value.toString());
	}

	@Override
	public boolean contains(Long container, Long contained) {
		return container >= contained;
	}
}
