package com.emarte.regurgitator.core.type;

import java.util.*;

public class ListOfStringType extends AbstractCollectionType<List<String>> {
	private static StringType STRING = new StringType();

	public String getId() {
		return "LIST_OF_STRING";
	}

	@Override
	public List<String> createNew() {
		return new ArrayList<String>();
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof List && validateCollection((Collection) value, STRING);
	}

	@Override
	public List<String> convert(Object value) {
		if (validate(value)) {
			return (List<String>) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value, STRING);
		}

		if (STRING.validate(value)) {
			return (List<String>) STRING.toCollectionOf((String) value, createNew(), STRING);
		}

		return Arrays.asList(STRING.convert(value));
	}
}
