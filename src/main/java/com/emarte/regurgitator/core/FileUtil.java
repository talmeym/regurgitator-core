package com.emarte.regurgitator.core;

import java.io.*;

public final class FileUtil {
    public static final String CLASSPATH_PREFIX = "classpath:";

    public static InputStream getInputStreamForFile(String filepath) throws IOException {
        if (filepath.startsWith(CLASSPATH_PREFIX)) {
            InputStream inputStream = FileUtil.class.getResourceAsStream(filepath.substring(CLASSPATH_PREFIX.length()));
            checkInputStream(inputStream, filepath);
            return inputStream;
        }

        return new FileInputStream(filepath);
    }

    private static void checkInputStream(InputStream inputStream, String filepath) throws IOException {
        if (inputStream == null) {
            throw new FileNotFoundException("File not found on classpath: " + filepath);
        }
        if (inputStream.available() == 0) {
            throw new IllegalArgumentException("File invalid - file is empty");
        }
    }

    public static void checkResource(String templateName) {
        try {
            FileUtil.getInputStreamForFile(templateName);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Error with resource: " + ioe.getMessage());
        }
    }

    public static String streamToString(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = 0;

        while((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }

        return new String(output.toByteArray());
    }
}
