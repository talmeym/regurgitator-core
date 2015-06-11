package com.emarte.regurgitator.core;

import java.util.*;

abstract class Container<TYPE extends HasId> extends Identifiable {

    private final List<TYPE> items;
    private final Map<Object, TYPE> itemsById;

    Container(Object id, List<TYPE> items) {
        super(id);
        this.items = items;
        itemsById = new HashMap<Object, TYPE>();

        for (TYPE item : items) {
            itemsById.put(item.getId(), item);
        }
    }

    void add(TYPE item) {
        if (itemsById.containsKey(item.getId())) {
            throw new IllegalArgumentException("Item already exists: " + item.getId());
        }

        items.add(item);
        itemsById.put(item.getId(), item);
    }

	List<Object> ids() {
		return new ArrayList<Object>(itemsById.keySet());
	}

    boolean contains(Object id) {
        return itemsById.containsKey(id);
    }

    TYPE get(Object id) {
        return itemsById.get(id);
    }

    List<TYPE> get(Collection<Object> ids) {
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

    List<TYPE> getAll() {
        return Collections.unmodifiableList(items);
    }

    int size() {
        return items.size();
    }
}
