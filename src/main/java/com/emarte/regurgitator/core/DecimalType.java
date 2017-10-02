package com.emarte.regurgitator.core;

import java.util.Collection;

public class DecimalType extends AbstractSingleType<Double> {
	@Override
	public Double createNew() {
		return 0D;
	}

	@Override
	public Double concat(Double prefix, Double suffix) {
		return prefix + suffix;
	}

	@Override
	public Double remove(Double existingValue, Double newValue) {
		return existingValue - newValue;
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof Double;
	}

	@Override
	public Double convert(Object value) {
		if (validate(value)) {
			return (Double) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value);
		}

		return Double.parseDouble(value.toString());
	}

	@Override
	public boolean contains(Double container, Double contained) {
		return container >= contained;
	}
}