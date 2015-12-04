package com.emarte.regurgitator.core;

public interface Cache<TYPE> {
	public boolean contains(Object key);
	public TYPE get(Object key);
	public void set(Object key, TYPE type);
}