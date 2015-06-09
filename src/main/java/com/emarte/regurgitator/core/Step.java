package com.emarte.regurgitator.core;

public interface Step extends HasId {
    public void execute(Message message) throws RegurgitatorException;
}
