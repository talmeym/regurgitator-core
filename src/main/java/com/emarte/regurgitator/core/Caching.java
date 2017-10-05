package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.CacheProvider.Cache;
import static com.emarte.regurgitator.core.Log.getLog;

public class Caching {
	private static final Log log = getLog(Caching.class);
	private static final ServiceLoader<CacheProvider> CACHE_PROVIDERS = ServiceLoader.load(CacheProvider.class);
	private static final CacheProvider DEFAULT_PROVIDER = new DefaultCacheProvider();

	public static <TYPE> Cache<TYPE> getCache(Class<TYPE> clazz) {
		for (CacheProvider provider : CACHE_PROVIDERS) {
			Cache<TYPE> cache = provider.getCache(clazz);

			if(cache != null) {
				log.debug("Using provided cache: {}", provider.getClass().getName());
				return cache;
			}
		}

		log.debug("Using default cache");
		return DEFAULT_PROVIDER.getCache(clazz);
	}

	private static class DefaultCacheProvider implements CacheProvider {
		private static final Log log = getLog(DefaultCacheProvider.class);
		private static Map<Object, Object> DEFAULT_CACHE_DATA = new HashMap<Object, Object>();

		@Override
		public <TYPE> Cache<TYPE> getCache(Class<TYPE> clazz) {
			return new DefaultCache<TYPE>(clazz);
		}

		private class DefaultCache<TYPE> implements Cache<TYPE> {
			Class clazz;

			private DefaultCache(Class<TYPE> clazz) {
				this.clazz = clazz;
			}

			@Override
			public boolean contains(Object key) {
				String trueKey = trueKey(key);
				log.debug("Checking presence of true key '{}'", trueKey);
				return DEFAULT_CACHE_DATA.containsKey(trueKey);
			}

			@Override
			@SuppressWarnings("unchecked")
			public TYPE get(Object key) {
				String trueKey = trueKey(key);
				log.debug("Retrieving object using true key of '{}'", trueKey);
				return (TYPE) DEFAULT_CACHE_DATA.get(trueKey);
			}

			@Override
			public void set(Object key, TYPE value) {
				String trueKey = trueKey(key);
				log.debug("Storing object with true key of '{}'", trueKey);
				DEFAULT_CACHE_DATA.put(trueKey, value);
			}

			private String trueKey(Object key) {
				return clazz.getName() + ":" + key.toString();
			}
		}
	}
}
