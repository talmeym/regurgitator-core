package com.emarte.regurgitator.core;

public interface ValueGenerator extends HasId {
	public Object generate() throws RegurgitatorException;
}
