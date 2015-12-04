package com.emarte.regurgitator.core;

import java.util.ServiceLoader;

public class Caching {
	private static Log log = Log.getLog(Caching.class);

	private static final ServiceLoader<CacheProvider> CACHE_PROVIDERS = ServiceLoader.load(CacheProvider.class);

	private static final CacheProvider DEFAULT_PROVIDER = new DefaultCacheProvider();

	public static <TYPE> Cache<TYPE> getCache(Class<TYPE> clazz) {
		for (CacheProvider provider : CACHE_PROVIDERS) {
			Cache<TYPE> cache = provider.getCache(clazz);

			if(cache != null) {
				log.debug("Using provided cache: " + provider.getClass().getName());
				return cache;
			}
		}

		log.debug("Using default cache");
		return DEFAULT_PROVIDER.getCache(clazz);
	}
}
