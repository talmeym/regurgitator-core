package com.emarte.regurgitator.core;

public class Caching {
	private static Log log = Log.getLog(Caching.class);
	private static Cache cacheToUse;

	public static Cache getCache(Class clazz) {
		if(cacheToUse == null) {
			log.debug("Using default cache");
			cacheToUse = new DefaultCache(clazz);
		}

		return cacheToUse;
	}

	public static void setCache(Cache cache) {
		cacheToUse = cache;
	}

	public static interface Cache {
		public boolean hasValue(Object key);

		public Object getValue(Object key);

		public Object setValue(Object key, Object value);
	}
}
