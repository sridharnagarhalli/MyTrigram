package com.sridhar.util;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FileUtilsTest {

    private String fileName;

    @Test
    public void writeFile() throws IOException {

        fileName = FileUtils.generateFileName();
        assertTrue(fileName.endsWith(".txt"));
        assertTrue(fileName.startsWith("TrigramOutput_"));

        FileUtils.writeFile("text to write", fileName);

        String content = new String(Files.readAllBytes(Paths.get(fileName)));

        assertNotNull(content);
        assertEquals("text to write", content);
        Files.delete(Paths.get(fileName));
    }

    @Test
    public void getFileMultiLineAsString() throws IOException, URISyntaxException {
        String fileString = FileUtils.getFileAsString("testFileMultiLine.txt");
        assertEquals("There was a rushing, whizzing, throbbing noise in the air.  A great\n" +
                        "body, like that of some immense bird, sailed along, casting a grotesque\n" +
                        "shadow on the ground below.  An elderly man, who was seated on the\n" +
                        "porch of a large house, started to his feet in alarm.", fileString);
    }

}