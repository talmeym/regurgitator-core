package com.emarte.regurgitator.core.type;

import java.util.*;

public class ListOfDecimalType extends AbstractCollectionType<List<Double>> {
	private static DecimalType DECIMAL = new DecimalType();
	private static StringType STRING = new StringType();

	public String getId() {
		return "LIST_OF_DECIMAL";
	}

	@Override
	public List<Double> createNew() {
		return new ArrayList<Double>();
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof List && validateCollection((Collection) value, DECIMAL);
	}

	@Override
	public List<Double> convert(Object value) {
		if (validate(value)) {
			return (List<Double>) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value, DECIMAL);
		}

		if (STRING.validate(value)) {
			return (List<Double>) STRING.toCollectionOf((String) value, createNew(), DECIMAL);
		}

		return Arrays.asList(DECIMAL.convert(value));
	}
}
