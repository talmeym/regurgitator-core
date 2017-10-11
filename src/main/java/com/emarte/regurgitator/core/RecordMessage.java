/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.io.*;
import java.util.Collection;

public final class RecordMessage extends Identifiable implements Step {
    private final String folderPath;

    public RecordMessage(Object id, String folderPath) {
        super(id);
        this.folderPath = folderPath;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        try {
            Writer out = getWriter();
            Collection<Parameters> contexts = message.contexts();

            for(Parameters context: contexts) {
                out.write("# " + context.getId() + "\n\n");

                for(Object id: context.ids()) {
                    Parameter parameter = context.get(id);
                    out.write(context.getId() + "." + parameter.getId() + "=" + parameter.getValue() + "\n");
                }

                out.write("\n");
            }

            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RegurgitatorException("Error recording message", e);
        }
    }

    private OutputStreamWriter getWriter() throws IOException {
        if(folderPath != null) {
            File folder = new File(folderPath);

            if(!folder.exists()) {
                throw new FileNotFoundException("Folder not found: " + folder.getAbsolutePath());
            }

            File file = new File(folder, System.currentTimeMillis() + ".message");

            if(!file.exists()) {
                if(!file.createNewFile()) {
                    throw new IllegalStateException("Cannot create file: " + file.getAbsolutePath());
                }
            }

            return new FileWriter(file);
        }

        return new OutputStreamWriter(System.out);
    }
}
