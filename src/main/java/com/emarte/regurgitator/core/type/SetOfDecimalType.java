package com.emarte.regurgitator.core.type;

import java.util.*;

public class SetOfDecimalType extends AbstractCollectionType<Set<Double>> {
	private static DecimalType DECIMAL = new DecimalType();
	private static StringType STRING = new StringType();

	@Override
	public Set<Double> createNew() {
		return new LinkedHashSet<Double>();
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof Set && validateCollection((Collection) value, DECIMAL);
	}

	@Override
	public Set<Double> convert(Object value) {
		if (validate(value)) {
			return (Set<Double>) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value, DECIMAL);
		}

		if (STRING.validate(value)) {
			return (Set<Double>) STRING.toCollectionOf((String) value, createNew(), DECIMAL);
		}

		return new LinkedHashSet<Double>(Arrays.asList(DECIMAL.convert(value)));
	}
}
