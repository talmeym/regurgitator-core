/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class Log {
    private Object id;
    private final Logger logger;

    private Log(Class<?> clazz) {
        logger = getLogger(clazz);
    }

    private Log(HasId hasId) {
        id = hasId.getId();
        logger = getLogger(hasId.getClass());
    }

    public void debug(String message, Object... objs) {
        logger.debug(buildLogEntry(message), objs);
    }

    public void info(String message, Object... objs) {
        logger.info(buildLogEntry(message), objs);
    }

    public void warn(String message, Object... objs) {
        logger.warn(buildLogEntry(message), objs);
    }

    public void error(String message, Object... objs) {
        logger.error(buildLogEntry(message), objs);
    }

    public void error(String message, Throwable throwable) {
        logger.error(buildLogEntry(message), throwable);
    }

    private String buildLogEntry(String message) {
        return "[" + ((id != null ? "'" + id + "'" : "no-id") + "/" + Thread.currentThread().getId()) + "]: " + message;
    }

    public static Log getLog(Class<?> clazz) {
        return new Log(clazz);
    }

    public static Log getLog(HasId hasId) {
        return new Log(hasId);
    }
}
