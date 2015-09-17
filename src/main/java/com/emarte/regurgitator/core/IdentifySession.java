package com.emarte.regurgitator.core;

final class IdentifySession extends Identifiable implements Step {
    private final Log log = Log.getLog(this);
    private final ContextLocation location;

    IdentifySession(Object id, ContextLocation location) {
        super(id);
        this.location = location;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        Parameter parameter = message.getContextValue(location);
        log.debug("Setting message session id from context location '" + location + '\'');

        if(parameter == null) {
            throw new IllegalStateException("No session id value found in context location '" + location + '\'');
        }

        Object value = parameter.getValue();

        if(!message.hasSession()) {
            message.setSessionId(value);
        } else {
            log.warn("Session already identified");
        }
    }
}
