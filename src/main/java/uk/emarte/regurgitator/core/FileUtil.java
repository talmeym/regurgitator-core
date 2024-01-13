/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.io.*;

public final class FileUtil {
    public static final String CLASSPATH_PREFIX = "classpath:";

    public static InputStream getInputStreamForFile(String filePath) throws IOException {
        if (filePath.startsWith(CLASSPATH_PREFIX)) {
            InputStream inputStream = FileUtil.class.getResourceAsStream(filePath.substring(CLASSPATH_PREFIX.length()));
            checkInputStream(inputStream, filePath);
            return inputStream;
        }

        return new FileInputStream(filePath);
    }

    private static void checkInputStream(InputStream inputStream, String filePath) throws IOException {
        if (inputStream == null) {
            throw new FileNotFoundException("File not found on classpath: " + filePath);
        }
        if (inputStream.available() == 0) {
            throw new EOFException("File invalid - file is empty");
        }
    }

    public static String streamToString(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        return output.toString();
    }
}
