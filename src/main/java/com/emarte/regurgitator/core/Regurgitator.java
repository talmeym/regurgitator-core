/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.io.IOException;
import java.util.Properties;

import static com.emarte.regurgitator.core.FileUtil.getInputStreamForFile;
import static com.emarte.regurgitator.core.Log.getLog;

public final class Regurgitator extends Identifiable {
    private static final Log LOG = getLog(Regurgitator.class);
    private static final String STRING_TYPE_SEPARATOR = "string.type.separator";

    static {
        try {
            Properties properties = new Properties();

            try {
                LOG.debug("Loading regurgitator properties");
                properties.load(getInputStreamForFile("classpath:/regurgitator.properties"));
                LOG.debug("Loaded {} properties", properties.size());

                if(properties.containsKey(STRING_TYPE_SEPARATOR)) {
                    String separator = properties.getProperty(STRING_TYPE_SEPARATOR);

                    if(separator.length() == 1) {
                        LOG.debug("Setting string type separator to '{}'", separator);
                        StringType.setSeparator(separator.charAt(0));
                    } else {
                        LOG.warn("Invalid string type separator - separator value must be a single character - continuing with default");
                    }
                }
            } catch(IOException ioe) {
                LOG.debug("Could not find regurgitator.properties '{}', continuing without properties", ioe.getMessage());
            }
        } catch (Exception e) {
            LOG.error("Error initialising regurgitator", e);
        }
    }

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
