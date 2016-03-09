package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

public final class Regurgitator extends Identifiable {
    private final Log log = getLog(this);
    private final Step rootStep;

    public Regurgitator(String id, Step rootStep) {
        super(id);
        this.rootStep = rootStep;
    }

    public void processMessage(Message message) throws RegurgitatorException {
        try {
            log.debug("Incoming message");
            rootStep.execute(message);
			log.debug("Message processed");
        } catch(RegurgitatorException re) {
            throw re;
        } catch (Exception e) {
            throw new RegurgitatorException("Error processing message", e);
        }
    }
}
