/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

import static uk.emarte.regurgitator.core.Log.getLog;

public final class BuildParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueBuilder builder;

    public BuildParameter(Object id, ParameterPrototype prototype, String context, ValueBuilder builder, List<ValueProcessor> processors, boolean optional) {
        super(id, prototype, context, processors, optional);
        this.builder = builder;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        log.debug("Building parameter value");
        Object value = builder.build(message);

        log.debug("Built value for parameter '{}'", prototype.getName());
        return value;
    }
}
