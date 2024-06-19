/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

public abstract class ParameterExtractor extends Identifiable implements Step {
    protected final ParameterPrototype prototype;
    protected final String context;
    private final List<ValueProcessor> processors;
    private final boolean optional;

    public ParameterExtractor(Object id, ParameterPrototype prototype, String context, List<ValueProcessor> processors, boolean optional) {
        super(id);
        this.prototype = prototype;
        this.context = context;
        this.processors = processors;
        this.optional = optional;
    }

    @Override
    public final void execute(Message message) throws RegurgitatorException {
        ParameterType<?> type = prototype.getType();
        Object value = extractValue(message);

        if(processors.size() > 0 && value != null) {
            for(ValueProcessor processor: processors) {
                value = processor.process(value, message);
            }
        }

        if(value != null) {
            message.getContext(context).setValue(new Parameter(prototype, type.convert(value)));
        } else if(!optional) {
            throw new RegurgitatorException("Parameter value not found and parameter not optional");
        }
    }

    public abstract Object extractValue(Message message) throws RegurgitatorException;
}
