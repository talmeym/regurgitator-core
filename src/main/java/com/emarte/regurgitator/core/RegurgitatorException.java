package com.emarte.regurgitator.core;

public class RegurgitatorException extends Exception {

    public RegurgitatorException(String message) {
        super(message);
    }

    public RegurgitatorException(String message, Exception e) {
        super(message, e);
    }
}
