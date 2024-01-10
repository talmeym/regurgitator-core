/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;
import static uk.emarte.regurgitator.core.Log.getLog;
import static uk.emarte.regurgitator.core.StringType.stringify;

public class SetAtIndexProcessor extends CollectionProcessor {
    private static final Log log = getLog(SetAtIndexProcessor.class);

    private final ValueSource indexSource;
    private final ValueSource valueSource;

    public SetAtIndexProcessor(ValueSource indexSource, ValueSource valueSource) {
        this.indexSource = indexSource;
        this.valueSource = valueSource;
    }

    @Override
    public Object processCollection(Collection<?> collection, Message message) throws RegurgitatorException {
        List<Object> list = new ArrayList<>(collection);
        Object indexObj = indexSource.getValue(message, log);
        int index = parseInt(stringify(indexObj));
        Object valueObj = valueSource.getValue(message, log);
        log.debug("Setting at index '{}' with value '{}'", index, valueObj);
        list.set(index, valueObj);
        return list;
    }
}
