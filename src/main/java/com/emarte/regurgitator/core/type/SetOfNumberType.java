package com.emarte.regurgitator.core.type;

import java.util.*;

public class SetOfNumberType extends AbstractCollectionType<Set<Long>> {
	private static NumberType NUMBER = new NumberType();
	private static StringType STRING = new StringType();

	public String getId() {
		return "SET_OF_NUMBER";
	}

	@Override
	public Set<Long> createNew() {
		return new LinkedHashSet<Long>();
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof Set && validateCollection((Collection) value, NUMBER);
	}

	@Override
	public Set<Long> convert(Object value) {
		if (validate(value)) {
			return (Set<Long>) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value, NUMBER);
		}

		if (STRING.validate(value)) {
			return (Set<Long>) STRING.toCollectionOf((String) value, createNew(), NUMBER);
		}

		return new LinkedHashSet<Long>(Arrays.asList(NUMBER.convert(value)));
	}
}
