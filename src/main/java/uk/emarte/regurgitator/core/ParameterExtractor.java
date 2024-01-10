/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

public abstract class ParameterExtractor extends Identifiable implements Step {
    private final ParameterPrototype prototype;
    private final String context;
    private final List<ValueProcessor> processors;

    public ParameterExtractor(Object id, ParameterPrototype prototype, String context, List<ValueProcessor> processors) {
        super(id);
        this.prototype = prototype;
        this.context = context;
        this.processors = processors;
    }

    public ParameterPrototype getPrototype() {
        return prototype;
    }

    public String getContext() {
        return context;
    }

    @Override
    public final void execute(Message message) throws RegurgitatorException {
        ParameterType<?> type = prototype.getType();
        Object value = extractValue(message);

        if(processors.size() > 0) {
            for(ValueProcessor processor: processors) {
                value = processor.process(value, message);
            }
        }

        message.getContext(context).setValue(new Parameter(prototype, type.convert(value)));
    }

    public abstract Object extractValue(Message message) throws RegurgitatorException;
}
