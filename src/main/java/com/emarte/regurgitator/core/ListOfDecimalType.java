package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.CoreTypes.*;
import static java.util.Collections.singletonList;

public final class ListOfDecimalType extends AbstractCollectionType<Double, List<Double>> {
	public ListOfDecimalType() {
		super(DECIMAL);
	}

	@Override
	public List<Double> createNew() {
		return new ArrayList<Double>();
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof List && validateCollection((Collection) value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Double> convert(Object value) {
		if (validate(value)) {
			return (List<Double>) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value);
		}

		if (STRING.validate(value)) {
			return STRING.toCollectionOf((String) value, createNew(), DECIMAL);
		}

		return singletonList(DECIMAL.convert(value));
	}
}
