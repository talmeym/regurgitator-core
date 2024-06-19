/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListProcessor extends CollectionProcessor {
    private final List<ValueProcessor> processors;

    public ListProcessor(List<ValueProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public Object processCollection(Collection<?> collection, Message message) throws RegurgitatorException {
        List<Object> result = new ArrayList<>(collection.size());

        for (Object object : collection) {
            for (ValueProcessor processor : processors) {
                object = processor.process(object, message);
            }

            result.add(object);
        }

        return result;
    }
}
