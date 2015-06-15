package com.emarte.regurgitator.core;

public interface ValueBuilder {
    Object build(Message message) throws RegurgitatorException;
}
