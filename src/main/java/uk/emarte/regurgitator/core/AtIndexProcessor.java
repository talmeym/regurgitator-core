/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static uk.emarte.regurgitator.core.Log.getLog;
import static uk.emarte.regurgitator.core.StringType.stringify;

public final class AtIndexProcessor extends CollectionProcessor {
    private static final Log log = getLog(AtIndexProcessor.class);
    private final ValueSource indexSource;

    public AtIndexProcessor(ValueSource indexSource) {
        this.indexSource = indexSource;
    }

    @Override
    public Object processCollection(Collection<?> collection, Message message) throws RegurgitatorException {
        Object indexObj = indexSource.getValue(message, log);

        if(indexObj != null) {
            try {
                int index = parseInt(stringify(indexObj)), i = 0;

                if (index >= 0 && index < collection.size()) {
                    log.debug("Returning index '{}' of value '{}'", index, collection);

                    for (Object object : collection) {
                        if (i++ == index) {
                            return object;
                        }
                    }
                }

                throw new RegurgitatorException(format("Index '%d' out of bounds for value '%s'", index, collection));
            } catch(NumberFormatException nfe) {
                throw new RegurgitatorException(format("Index value '%s' is not a number", stringify(indexObj)));
            }
        }

        throw new RegurgitatorException("Value for index could not be found");
    }
}
