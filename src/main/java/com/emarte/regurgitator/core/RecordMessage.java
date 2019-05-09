/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import static com.emarte.regurgitator.core.StringType.stringify;

final class RecordMessage extends Identifiable implements Step {
    private final String folderPath;

    RecordMessage(Object id, String folderPath) {
        super(id);
        this.folderPath = folderPath;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        try {
            long now = System.currentTimeMillis();
            Writer out = getWriter(now);
            out.write("{\r\n");
            Iterator<Parameters> contextIterator = message.contexts().iterator();

            while(contextIterator.hasNext()) {
                Parameters context = contextIterator.next();
                out.write("\t\"" + context.getId() + "\": {\r\n");


                List<Object> ids = context.ids();

                for(int i = 0; i < ids.size(); i++) {
                    Object id = ids.get(i);
                    Parameter parameter = context.get(id);
                    out.write("\t\t\"" + escape(id) + "\": \"" + escape(parameter.getValue()) + "\"");

                    if(i < ids.size() - 1) {
                        out.write(",\r\n");
                    }
                }

                out.write("\r\n\t}");

                if(contextIterator.hasNext()) {
                    out.write(",\r\n");
                }
            }

            out.write("\r\n}");
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RegurgitatorException("Error recording message", e);
        }
    }

    private OutputStreamWriter getWriter(long now) throws IOException {
        if(folderPath != null) {
            File folder = new File(folderPath);

            if(!folder.exists()) {
                throw new FileNotFoundException("Folder not found: " + folder.getAbsolutePath());
            }

            File file = new File(folder, now + "." + getId() + ".json");

            if(!file.exists()) {
                if(!file.createNewFile()) {
                    throw new IllegalStateException("Cannot create file: " + file.getAbsolutePath());
                }
            }

            return new FileWriter(file);
        }

        return new OutputStreamWriter(System.out);
    }

    private String escape(Object value1) {
        String value = stringify(value1);

        if(value.contains("\"")) {
            value = value.replace("\"", "\\\"");
        }

        return value;
    }
}
