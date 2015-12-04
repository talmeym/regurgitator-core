package com.emarte.regurgitator.core;

public interface CacheProvider {
	public <TYPE> Cache<TYPE> getCache(Class<TYPE> clazz);
}
