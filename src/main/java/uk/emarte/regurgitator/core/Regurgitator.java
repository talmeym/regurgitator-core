/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import static uk.emarte.regurgitator.core.Log.getLog;

public final class Regurgitator extends Identifiable {
    private final Log log = getLog(this);
    private final Step rootStep;

    public Regurgitator(String id, Step rootStep) {
        super(id);
        this.rootStep = rootStep;
    }

    public void processMessage(Message message) throws RegurgitatorException {
        try {
            log.info("Incoming message");
            rootStep.execute(message);
            log.info("Message processed");
        } catch(RegurgitatorException re) {
            throw re;
        } catch (Exception e) {
            throw new RegurgitatorException("Error processing message", e);
        }
    }
}
