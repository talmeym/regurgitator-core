package com.emarte.regurgitator.core;

import java.util.*;

public class DefaultCache implements Caching.Cache {
	private static final Log log = Log.getLog(DefaultCache.class);
	private static Map<Object, Object> CACHE = new HashMap<Object, Object>();

	private Class clazz;

	public DefaultCache(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean hasValue(Object key) {
		return CACHE.containsKey(trueKey(key));
	}

	@Override
	public Object getValue(Object key) {
		String trueKey = trueKey(key);
		log.debug("Retrieving object using true key of '" + trueKey + "'");
		return CACHE.get(trueKey);
	}

	@Override
	public Object setValue(Object key, Object value) {
		String trueKey = trueKey(key);
		log.debug("Storing object with true key of '" + trueKey + "'");
		Object currentValue = CACHE.get(trueKey);
		CACHE.put(trueKey, value);
		return currentValue;
	}

	private String trueKey(Object key) {
		return clazz.getName() + ":" + key.toString();
	}
}
