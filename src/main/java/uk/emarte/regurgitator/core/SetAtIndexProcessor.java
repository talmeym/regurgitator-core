/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
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
        Object indexObj = indexSource.getValue(message, log);
        Object valueObj = valueSource.getValue(message, log);

        if(indexObj != null && valueObj != null) {
            try {
                int index = parseInt(stringify(indexObj));

                if (index >= 0 && index < collection.size()) {
                    List<Object> list = new ArrayList<>(collection);
                    log.debug("Setting index '{}' of value '{}' to '{}'", index, list, valueObj);
                    list.set(index, valueObj);
                    return list;
                }
            } catch(NumberFormatException nfe) {
                throw new RegurgitatorException(format("Index value '%s' is not a number", stringify(indexObj)));
            }
        }

        if(indexObj == null) {
            throw new RegurgitatorException("Value for index could not be found");
        }

        throw new RegurgitatorException("Value to set could not be found");
    }
}
