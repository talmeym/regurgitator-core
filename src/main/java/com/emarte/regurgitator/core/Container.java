/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.*;

import static java.util.Collections.unmodifiableList;

public abstract class Container<TYPE extends HasId> extends Identifiable {

    private final List<TYPE> items;
    private final Map<Object, TYPE> itemsById;

    protected Container(Object id, List<TYPE> items) {
        super(id);
        this.items = items;
        itemsById = new HashMap<Object, TYPE>();

        for (TYPE item : items) {
            itemsById.put(item.getId(), item);
        }
    }

    protected void add(TYPE item) {
        if (itemsById.containsKey(item.getId())) {
            throw new IllegalArgumentException("Item already exists: " + item.getId());
        }

        items.add(item);
        itemsById.put(item.getId(), item);
    }

    protected List<Object> ids() {
        return new ArrayList<Object>(itemsById.keySet());
    }

    protected boolean contains(Object id) {
        return itemsById.containsKey(id);
    }

    public TYPE get(Object id) {
        return itemsById.get(id);
    }

    protected List<TYPE> get(Collection<Object> ids) {
        List<TYPE> result = new ArrayList<TYPE>();

        for(Object id: ids) {
            if(contains(id)) {
                result.add(get(id));
            } else {
                throw new IllegalArgumentException("Item not found: " + id);
            }
        }

        return result;
    }

    protected List<TYPE> getAll() {
        return unmodifiableList(items);
    }

    protected int size() {
        return items.size();
    }
}
