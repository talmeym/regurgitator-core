package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.CoreTypes.*;
import static java.util.Collections.singletonList;

public final class SetOfDecimalType extends AbstractCollectionType<Double, Set<Double>> {
	public SetOfDecimalType() {
		super(DECIMAL);
	}

	@Override
	public Set<Double> createNew() {
		return new LinkedHashSet<Double>();
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof Set && validateCollection((Collection) value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Double> convert(Object value) {
		if (validate(value)) {
			return (Set<Double>) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value);
		}

		if (STRING.validate(value)) {
			return STRING.toCollectionOf((String) value, createNew(), DECIMAL);
		}

		return new LinkedHashSet<Double>(singletonList(DECIMAL.convert(value)));
	}
}
