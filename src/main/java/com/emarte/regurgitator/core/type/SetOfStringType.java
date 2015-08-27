package com.emarte.regurgitator.core.type;

import java.util.*;

public class SetOfStringType extends AbstractCollectionType<Set<String>> {
	private static StringType STRING = new StringType();

	@Override
	public Set<String> createNew() {
		return new LinkedHashSet<String>();
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof Set && validateCollection((Collection) value, STRING);
	}

	@Override
	public Set<String> convert(Object value) {
		if (validate(value)) {
			return (Set<String>) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value, STRING);
		}

		if (STRING.validate(value)) {
			return (Set<String>) STRING.toCollectionOf((String) value, createNew(), STRING);
		}

		return new LinkedHashSet<String>(Arrays.asList(STRING.convert(value)));
	}
}
