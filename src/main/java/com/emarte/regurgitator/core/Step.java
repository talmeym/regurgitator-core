package com.emarte.regurgitator.core;

public interface Step extends HasId {
    void execute(Message message) throws RegurgitatorException;
}
