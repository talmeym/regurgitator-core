/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

public final class ValueSource {

    private final ContextLocation source;
    private final String value;

    public ValueSource(ContextLocation source, String value) {
        this.source = source;
        this.value = value;
    }

    public Object getValue(Message message, Log log) throws RegurgitatorException {
        if (source != null) {
            Parameter parameter = message.getContextValue(source);

            if (parameter != null) {
                log.debug("Retrieved value from context location '{}'", source);
                return parameter.getValue();
            } else if (value != null) {
                log.debug("defaulting to static value '{}'", value);
                return value;
            }

            log.debug("No value found at context location '" + source + "'");
            return null;
        }

        log.debug("Using static value '{}'", value);
        return value;
    }
}
