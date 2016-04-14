package com.emarte.regurgitator.core;

public class Log {
    private Class clazz;
	private Object id;

    private Log(Class clazz) {
        this.clazz = clazz;
    }

	public Log(HasId hasId) {
		this.clazz = hasId.getClass();
		id = hasId.getId();
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

    public void error(String message, Throwable throwable) {
        System.out.println("ERROR [" + getIdentifier() + "]: " + message);
		throwable.printStackTrace();
    }

	private String getIdentifier() {
		String result = clazz.getName();

		if(id != null) {
			result += ":" + id;
		}

		result += "/" + Thread.currentThread().getId();

		return result;
	}

	public static Log getLog(Class clazz) {
		return new Log(clazz);
	}

	public static Log getLog(HasId hasId) {
		return new Log(hasId);
	}
}
