package com.emarte.regurgitator.core;

public interface ValueProcessor {
    Object process(Object value, Message message) throws RegurgitatorException;
}
