package com.emarte.regurgitator.core;

public class Log {
    private Class clazz;

    private Log(Class clazz) {
        this.clazz = clazz;
    }

    public void debug(String message) {
        System.out.println("DEBUG [" + clazz.getName() + "]: " + message);
    }

    public void info(String message) {
        System.out.println("INFO [" + clazz.getName() + "]: " + message);
    }

    public void warn(String message) {
        System.out.println("WARN [" + clazz.getName() + "]: " + message);
    }

    public void error(String message) {
        System.out.println("ERROR [" + clazz.getName() + "]: " + message);
    }

	public static Log getLog(Class clazz) {
		return new Log(clazz);
	}
}
