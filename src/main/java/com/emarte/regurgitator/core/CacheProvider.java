/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

public interface CacheProvider {
    <TYPE> Cache<TYPE> getCache(Class<TYPE> clazz);

    interface Cache<TYPE> {
        boolean contains(Object key);
        TYPE get(Object key);
        void set(Object key, TYPE type);
        void clearCache();
    }
}
