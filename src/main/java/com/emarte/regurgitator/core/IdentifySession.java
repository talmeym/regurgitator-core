package com.emarte.regurgitator.core;

final class IdentifySession extends Identifiable implements Step {
    private static final Log log = Log.getLog(IdentifySession.class);

    private final ContextLocation location;

    IdentifySession(Object id, ContextLocation location) {
        super(id);
        this.location = location;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        Parameter parameter = message.getContextValue(location);
        log.debug("Setting session id from context '" + location + '\'');

        if(parameter == null) {
            throw new IllegalStateException("No session id value found in context location '" + location + '\'');
        }

        Object value = parameter.getValue();

        if(message.getSession() == null) {
            log.debug("Setting session id in message to '" + value + "'");
            message.setSessionId(value);
        } else {
            log.warn("Session already identified");
        }
    }
}
