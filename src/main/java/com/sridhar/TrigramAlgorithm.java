package com.sridhar;

import com.sridhar.logging.Logger;
import com.sridhar.util.FileUtils;
import com.sridhar.util.StringUtils;
import com.sridhar.util.TrigramUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class TrigramAlgorithm {

    private final static String FILE_NAME = "Tom_SwiftChapt1And2.txt";

    public static void main(String[] args) throws IOException, URISyntaxException {

        /*
        1. Read from file
        2. Format it to remove what we don't need so we can create a clean data structure, an array would be best as we need to look ahead and we will know the size from the beginning.
        3. Create a trigram map, based on reading three adjacent words.  First two are the key, third is the value then move to the next word and do the same. Have made all words lowercase for ease of lookup.
        4. Future enhancement would be to use a word metadata class so we would know what capitalisation and punctuation a word originally had in the initial document.
        5. Then loop round the map, use a random pair key, and use this to generate the next random word.
        6. Use the new word pair at the end of the text to do another lookup.
        7. Add some punctuation routines to take care of basic capitalisation, sentences and paragraphs.
        8. Write the file out in the root directory
        */

        Logger.log("Processing started...");

        String fileContent = FileUtils.getFileAsString(FILE_NAME);

        String textWithoutPunctuations = StringUtils.removePunctuation(fileContent);

        String textWithNoNewLines = StringUtils.removeNewLine(textWithoutPunctuations);

        String textWithNoDoubleSpaces = StringUtils.removeWhiteSpace(textWithNoNewLines);

        String textLowercase = textWithNoDoubleSpaces.toLowerCase();

        String[] textArray = StringUtils.splitString(textLowercase, StringUtils.SPACE);

        Map<String, List<String>> tigramMap = TrigramUtils.generateTigramMap(textArray);

        String tigramText = TrigramUtils.generateTigramText(tigramMap);

        String fileName = FileUtils.generateFileName();
        FileUtils.writeFile(tigramText, fileName);

        Logger.log("Processing Completed...");

    }

}
