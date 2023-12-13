/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListProcessor implements ValueProcessor {
    private final List<ValueProcessor> processors;

    public ListProcessor(List<ValueProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public Object process(Object value, Message message) throws RegurgitatorException {
        Collection<?> values = getCollection(value);
        List<Object> result = new ArrayList<>(values.size());

        for(Object object: values) {
            for (ValueProcessor processor : processors) {
                object = processor.process(object, message);
            }

            result.add(object);
        }

        return result;
    }

    private Collection<?> getCollection(Object value) {
        if(!(value instanceof Collection)) {
            List<Object> list = new ArrayList<>();
            list.add(value);
            return list;
        }

        return (Collection<?>) value;
    }
}
