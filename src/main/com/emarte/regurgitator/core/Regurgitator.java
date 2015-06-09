package com.emarte.regurgitator.core;

public final class Regurgitator extends Identifiable {
    private static final Log log = Log.getLog(Regurgitator.class);

    private final Step rootStep;

    public Regurgitator(String name, Step rootStep) {
        super(name);
        this.rootStep = rootStep;
    }

    public void processMessage(Message message) throws RegurgitatorException {
        try {
            log.debug("Incoming message");
            rootStep.execute(message);
        } catch(RegurgitatorException re) {
            throw re;
        } catch (Exception e) {
            throw new RegurgitatorException("Error processing message", e);
        }
    }
}
