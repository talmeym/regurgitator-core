package com.emarte.regurgitator.core;

public class Log {
    private Class clazz;
	private HasId hasId;

    private Log(Class clazz) {
        this.clazz = clazz;
    }

	public Log(Object object) {
		this.clazz = object.getClass();

		if(object instanceof HasId) {
			hasId = (HasId) object;
		}
	}

	public void debug(String message) {
        System.out.println("DEBUG [" + getIdentifier() + "]: " + message);
    }

	public void info(String message) {
        System.out.println("INFO [" + getIdentifier() + "]: " + message);
    }

    public void warn(String message) {
        System.out.println("WARN [" + getIdentifier() + "]: " + message);
    }

    public void error(String message) {
        System.out.println("ERROR [" + getIdentifier() + "]: " + message);
    }

	private String getIdentifier() {
		String result = clazz.getName();

		if(hasId != null) {
			result += ":" + hasId.getId();
		}

		result += "/" + Thread.currentThread().getId();

		return result;
	}

	public static Log getLog(Class clazz) {
		return new Log(clazz);
	}

	public static Log getLog(Object object) {
		return new Log(object);
	}
}
