package com.sridhar.util;

import com.sridhar.logging.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class FileUtils {

    private FileUtils() {}

    public static String getFileAsString(final String filename) throws IOException, URISyntaxException {

        try (InputStream inputStream = FileUtils.class.getResourceAsStream("/" + filename);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String contents = reader.lines()
                            .collect(Collectors.joining(System.lineSeparator()));
            return contents;
        }

    }

    public static String generateFileName() {
        String now = LocalDateTime.now().toString();
        String fileName = "TrigramOutput_" + now + ".txt";
        Logger.log("Generating file : " + fileName);
        return fileName;
    }

    public static void writeFile(final String fileText, final String fileName) throws IOException {
        Files.write(Paths.get(fileName), fileText.getBytes(StandardCharsets.UTF_8));
    }
}
