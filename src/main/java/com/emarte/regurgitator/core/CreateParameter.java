package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

final class CreateParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueSource valueSource;

    CreateParameter(Object id, ParameterPrototype prototype, String context, ValueSource valueSource, ValueProcessor processor) {
        super(id, prototype, context, processor);
        this.valueSource = valueSource;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        return valueSource.getValue(message, log);
    }
}
