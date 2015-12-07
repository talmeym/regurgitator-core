package com.emarte.regurgitator.core;

public interface CacheProvider {
	public <TYPE> Cache<TYPE> getCache(Class<TYPE> clazz);

	public static interface Cache<TYPE> {
		public boolean contains(Object key);
		public TYPE get(Object key);
		public void set(Object key, TYPE type);
	}
}
