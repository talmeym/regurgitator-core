package com.emarte.regurgitator.core;

public interface ValueValidator {
    boolean validate(Object value) throws RegurgitatorException;
}
