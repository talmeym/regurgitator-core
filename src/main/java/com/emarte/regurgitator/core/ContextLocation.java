package com.emarte.regurgitator.core;

public class ContextLocation {
    private static final String SEPARATOR = ":";
    public static final String SESSION_CONTEXT = "session";
    public static final String PARAMETER_CONTEXT = "parameters";

    private final String context;
    private final String name;

    public ContextLocation(String context, String name) {
        this.context = context;
        this.name = name;
    }

    public ContextLocation(String location) {
		boolean contextSpecified = location.contains(SEPARATOR);
		context = contextSpecified ? location.substring(0, location.indexOf(SEPARATOR)) : PARAMETER_CONTEXT;
		name = contextSpecified ? location.substring(location.indexOf(SEPARATOR) + 1) : location;
    }

    public String getContext() {
        return context;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return context + SEPARATOR + name;
    }
}
