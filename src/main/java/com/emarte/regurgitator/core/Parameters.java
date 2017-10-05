package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static com.emarte.regurgitator.core.Log.getLog;

public class Parameters extends Container<Parameter> {
    private final Log log = getLog(this);

    Parameters(Object id) {
        super(id, new ArrayList<Parameter>());
    }

    public void setValue(String name, Object value) {
        setValue(name, STRING, value);
    }

    public void setValue(String name, ParameterType type, Object value) {
        setValue(name, type, REPLACE, value);
    }

    public void setValue(String name, ParameterType type, ConflictPolicy policy, Object value) {
        setValue(new Parameter(new ParameterPrototype(name, type, policy), value));
    }

    public Object getValue(Object id) {
        return contains(id) ? get(id).getValue() : null;
    }

    public List<Object> ids() {
        return super.ids();
    }

    public boolean contains(Object id) {
        return super.contains(id);
    }

    public void setValue(Parameter parameter) {
        Object id = parameter.getId();

        if (contains(id)) {
            log.debug("Merging parameter '{}'", id);
            get(id).merge(parameter);
        } else {
            log.debug("Adding parameter '{}' with value '{}'", id, parameter.getValue());
            add(parameter);
        }
    }

    public int size() {
        return super.size();
    }

    public String toString() {
        List<Parameter> all = getAll();
        StringBuilder buffer = new StringBuilder(getId() + "[");

        for(Parameter parameter: all) {
            buffer.append(parameter.getId()).append("=").append(parameter.getValue()).append(",");
        }

        buffer.append("]");
        return buffer.toString();
    }
}
