/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

import static uk.emarte.regurgitator.core.Log.getLog;

public final class CreateParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueSource valueSource;

    public CreateParameter(Object id, ParameterPrototype prototype, String context, ValueSource valueSource, List<ValueProcessor> processors, boolean optional) {
        super(id, prototype, context, processors, optional);
        this.valueSource = valueSource;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        return valueSource.getValue(message, log);
    }
}
