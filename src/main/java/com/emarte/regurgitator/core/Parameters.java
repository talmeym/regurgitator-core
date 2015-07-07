package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;

public class Parameters extends Container<Parameter> {
    private static final Log log = Log.getLog(Parameters.class);

    Parameters(Object id) {
        super(id, new ArrayList<Parameter>());
    }

    public void setValue(String name, ParameterType type, Object value) throws RegurgitatorException {
        setValue(new Parameter(new ParameterPrototype(name, type, REPLACE), value));
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

    public void setValue(Parameter parameter) throws RegurgitatorException {
        Object id = parameter.getId();

        if (contains(id)) {
            log.debug("Merging parameter '" + id + "' into '" + getId() + "'");
            get(id).merge(parameter);
        } else {
            log.debug("Adding parameter '" + id + "' into '" + getId() + "' with value '" + parameter.getValue() + "'");
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
