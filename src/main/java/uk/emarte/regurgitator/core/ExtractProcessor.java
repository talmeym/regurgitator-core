/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.text.MessageFormat;
import java.text.ParseException;

import static java.lang.String.format;
import static uk.emarte.regurgitator.core.Log.getLog;
import static uk.emarte.regurgitator.core.StringType.stringify;

public final class ExtractProcessor implements ValueProcessor {
    private static final Log log = getLog(ExtractProcessor.class);
    private final String format;
    private final int index;

    public ExtractProcessor(String format, int index) {
        this.format = format;
        this.index = index;
    }

    @Override
    public Object process(Object value, Message message) throws RegurgitatorException {
        if(value != null) {
            String stringValue = stringify(value);

            try {
                log.debug("Extracting using '{}', index='{}'", format, index);
                MessageFormat formatter = new MessageFormat(format);
                Object[] objects = formatter.parse(stringValue);

                if(index >= 0 && index < objects.length) {
                    return objects[index];
                }

                throw new RegurgitatorException(format("Index '%s' for message format '%s' not found in value '%s'", index, format, value));
            } catch (ParseException e) {
                throw new RegurgitatorException(format("Error parsing value '%s'", stringValue), e);
            }
        }

        log.warn("No value to process");
        return null;
    }
}