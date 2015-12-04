package com.emarte.regurgitator.core;

import java.util.*;

public class DefaultCacheProvider implements CacheProvider {
	private static final Log log = Log.getLog(DefaultCacheProvider.class);
	private static Map<Object, Object> DEFAULT_CACHE_DATA = new HashMap<Object, Object>();


	@Override
	public <TYPE> Cache<TYPE> getCache(Class<TYPE> clazz) {
		return new DefaultCache<TYPE>(clazz);
	}

	public class DefaultCache<TYPE> implements Cache<TYPE> {
		Class clazz;

		public DefaultCache(Class<TYPE> clazz) {
			this.clazz = clazz;
		}

		@Override
		public boolean contains(Object key) {
			String trueKey = trueKey(key);
			log.debug("Checking presence of true key '" + trueKey + "'");
			return DEFAULT_CACHE_DATA.containsKey(trueKey);
		}

		@Override
		@SuppressWarnings("unchecked cast")
		public TYPE get(Object key) {
			String trueKey = trueKey(key);
			log.debug("Retrieving object using true key of '" + trueKey + "'");
			return (TYPE) DEFAULT_CACHE_DATA.get(trueKey);
		}

		@Override
		public void set(Object key, TYPE value) {
			String trueKey = trueKey(key);
			log.debug("Storing object with true key of '" + trueKey + "'");
			DEFAULT_CACHE_DATA.put(trueKey, value);
		}

		private String trueKey(Object key) {
			return clazz.getName() + ":" + key.toString();
		}
	}
}
