package com.emarte.regurgitator.core.type;

import com.emarte.regurgitator.core.*;

import java.util.*;

import static com.emarte.regurgitator.core.type.DefaultTypes.STRING;

public class StringType implements ParameterType<String> {
	public String getId() {
		return "STRING";
	}

	@Override
	public String createNew() {
		return "";
	}

	@Override
	public String concat(String prefix, String suffix) {
		return prefix.concat(suffix);
	}

	@Override
	public String remove(String existingValue, String newValue) {
		return existingValue.replace(newValue, "");
	}

	@Override
	public boolean validate(Object value) {
		return value instanceof String;
	}

	@Override
	public String convert(Object value) {
		if (validate(value)) {
			return (String) value;
		}

		if (value instanceof Collection) {
			return fromCollection((Collection) value, this);
		}

		return String.valueOf(value);
	}

	@Override
	public boolean contains(String container, String contained) {
		return container.contains(contained);
	}

	@Override
	public Collection toCollectionOf(String value, Collection collection, ParameterType type) {
		String[] strings = value.split(",");

		for (String string : strings) {
			collection.add(type.convert(string));
		}

		return collection;
	}

	@Override
	public String fromCollection(Collection value, ParameterType type) {
		StringBuilder buffer = new StringBuilder();

		for (Iterator iterator = value.iterator(); iterator.hasNext(); ) {
			buffer.append(type.convert(iterator.next()));

			if (iterator.hasNext()) {
				buffer.append(",");
			}
		}

		return buffer.toString();
	}

	public static String stringify(Parameter parameter) {
		return parameter != null ? (String) STRING.convert(parameter.getValue()) : null;
	}

	public static String stringify(Object value) {
		return value != null ? (String) STRING.convert(value) : null;
	}
}
