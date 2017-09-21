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
		sysout("DEBUG", message);
	}

	public void info(String message) {
		sysout("INFO", message);
	}

    public void warn(String message) {
		sysout("WARN", message);
	}

    public void error(String message) {
		syserr("ERROR", message);
	}

    public void error(String message, Throwable throwable) {
		syserr("ERROR", message);
		throwable.printStackTrace();
    }

	private void sysout(String level, String message) {
		System.out.println(level + " [" + getIdentifier() + "]: " + message);
	}

	private void syserr(String level, String message) {
		System.err.println(level + " [" + getIdentifier() + "]: " + message);
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
