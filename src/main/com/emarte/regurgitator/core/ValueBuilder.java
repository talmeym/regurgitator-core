package com.emarte.regurgitator.core;

public interface ValueBuilder {
    Object build(Parameters parameters) throws RegurgitatorException;
}
