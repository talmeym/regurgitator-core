/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.CoreTypes.STRING;

public final class StringType implements ParameterType<String> {
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
            return fromCollection((Collection) value);
        }

        return String.valueOf(value);
    }

    @Override
    public boolean contains(String container, String contained) {
        return container.contains(contained);
    }

    @Override
    public <TYPE, COLLECTION extends Collection<TYPE>> COLLECTION toCollectionOf(String value, COLLECTION collection, ParameterType<TYPE> type) {
        String[] strings = value.split(",");

        for (String string : strings) {
            collection.add(type.convert(string));
        }

        return collection;
    }

    @Override
    public String fromCollection(Collection value) {
        StringBuilder buffer = new StringBuilder();

        for (Iterator iterator = value.iterator(); iterator.hasNext(); ) {
            buffer.append(convert(iterator.next()));

            if (iterator.hasNext()) {
                buffer.append(",");
            }
        }

        return buffer.toString();
    }

    public static String stringify(Parameter parameter) {
        return parameter != null ? STRING.convert(parameter.getValue()) : null;
    }

    public static String stringify(Object value) {
        return value != null ? STRING.convert(value) : null;
    }
}
