/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

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

        private final Map<Class<?>, Cache<?>> CACHES = new HashMap<Class<?>, Cache<?>>();

        @Override
        @SuppressWarnings("unchecked")
        public <TYPE> Cache<TYPE> getCache(Class<TYPE> clazz) {
            if(!CACHES.containsKey(clazz)) {
                CACHES.put(clazz, new DefaultCache<TYPE>(clazz));
            }

            return (Cache<TYPE>) CACHES.get(clazz);
        }

        private static class DefaultCache<TYPE> implements Cache<TYPE> {
            private final Map<String, TYPE> CACHE_DATA = new HashMap<String, TYPE>();
            private final Class<TYPE> clazz;

            private DefaultCache(Class<TYPE> clazz) {
                this.clazz = clazz;
            }

            @Override
            public boolean contains(Object key) {
                String trueKey = trueKey(key);
                log.debug("Checking presence of true key '{}'", trueKey);
                return CACHE_DATA.containsKey(trueKey);
            }

            @Override
            public TYPE get(Object key) {
                String trueKey = trueKey(key);
                log.debug("Retrieving object using true key of '{}'", trueKey);
                return CACHE_DATA.get(trueKey);
            }

            @Override
            public void set(Object key, TYPE value) {
                String trueKey = trueKey(key);
                log.debug("Storing object with true key of '{}'", trueKey);
                CACHE_DATA.put(trueKey, value);
            }

            @Override
            public void clearCache() {
                for(Iterator<Map.Entry<String, TYPE>> iterator = CACHE_DATA.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, TYPE> entry = iterator.next();

                    if(entry.getKey().startsWith(clazz.getName() + ":")) {
                        log.debug("Clearing object with true key of '{}'", entry.getKey());
                        iterator.remove();
                    }
                }
            }

            private String trueKey(Object key) {
                return clazz.getName() + ":" + key.toString();
            }
        }
    }
}
