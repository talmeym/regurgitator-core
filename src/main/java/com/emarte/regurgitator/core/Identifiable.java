/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

public abstract class Identifiable implements HasId {
    private final Object id;

    public Identifiable(Object id) {
        if(id == null) {
            throw new NullPointerException("Id cannot be null");
        }

        this.id = id;
    }

    public Object getId() {
        return id;
    }
}
