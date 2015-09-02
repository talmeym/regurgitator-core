package com.emarte.regurgitator.core;

import java.util.*;

public class ListOfNumberType extends AbstractCollectionType<List<Long>> {
	private static NumberType NUMBER = new NumberType();
	private static StringType STRING = new StringType();

	@Override
	public List<Long> createNew() {
		return new ArrayList<Long>();
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof List && validateCollection((Collection) value, NUMBER);
	}

	@Override
	public List<Long> convert(Object value) {
		if (validate(value)) {
			return (List<Long>) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value, NUMBER);
		}

		if (STRING.validate(value)) {
			return (List<Long>) STRING.toCollectionOf((String) value, createNew(), NUMBER);
		}

		return Arrays.asList(NUMBER.convert(value));
	}
}
