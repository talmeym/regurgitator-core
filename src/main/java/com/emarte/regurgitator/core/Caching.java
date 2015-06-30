package com.emarte.regurgitator.core;

import java.util.*;

public class Caching {
	private static Log log = Log.getLog(Caching.class);
	private static Map<Object, Object> DEFAULT_CACHE = new HashMap<Object, Object>();
	private static Cache cacheToUse;

	public static Cache getCache(Class clazz) {
		if(cacheToUse == null) {
			log.debug("Using default cache");
			cacheToUse = buildDefaultCache(clazz);
		}

		return cacheToUse;
	}

	public static void setCache(Cache cache) {
		cacheToUse = cache;
	}

	private static Cache buildDefaultCache(final Class clazz) {
		return new Cache() {
			@Override
			public boolean hasValue(Object key) {
				return DEFAULT_CACHE.containsKey(trueKey(key));
			}

			@Override
			public Object getValue(Object key) {
				String trueKey = trueKey(key);
				log.debug("Retrieving using true key of '" + trueKey + "'");
				return DEFAULT_CACHE.get(trueKey);
			}

			@Override
			public Object setValue(Object key, Object value) {
				String trueKey = trueKey(key);
				log.debug("Setting with true key of '" + trueKey + "'");
				Object currentValue = DEFAULT_CACHE.get(trueKey);
				DEFAULT_CACHE.put(trueKey, value);
				return currentValue;
			}

			private String trueKey(Object key) {
				String trueKey = clazz.getName() + ":" + key.toString();
				return trueKey;
			}
		};
	}

	public static interface Cache {
		public boolean hasValue(Object key);

		public Object getValue(Object key);

		public Object setValue(Object key, Object value);
	}
}
