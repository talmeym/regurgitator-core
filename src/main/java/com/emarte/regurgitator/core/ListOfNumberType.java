package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.CoreTypes.*;
import static java.util.Collections.singletonList;

public final class ListOfNumberType extends AbstractCollectionType<Long, List<Long>> {
    public ListOfNumberType() {
        super(NUMBER);
    }

    @Override
    public List<Long> createNew() {
        return new ArrayList<Long>();
    }

    @Override
    public boolean validate(Object value) {
        return value instanceof List && validateCollection((Collection) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> convert(Object value) {
        if (validate(value)) {
            return (List<Long>) value;
        }

        if (value instanceof Collection) {
            return fromCollection((Collection) value);
        }

        if (STRING.validate(value)) {
            return STRING.toCollectionOf((String) value, createNew(), NUMBER);
        }

        return singletonList(NUMBER.convert(value));
    }
}
