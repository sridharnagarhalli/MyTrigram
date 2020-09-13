package com.sridhar.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TrigramUtils {

    private TrigramUtils() {
        //  private constructor for utility class
    }

    public static Map<String, List<String>> generateTigramMap(final String[] textArray) {

        Map<String, List<String>> tigramMap = generateFirstTigram(textArray);

        for (int i = 1; i < textArray.length - 2; i++) {
            String key = generateKey(textArray, i);
            String value = generateValue(textArray, i);
            if (tigramMap.containsKey(key)) {
                tigramMap.get(key).add(value);
            } else {
                addTigramKeyValue(tigramMap, key, value);
            }

        }

        return tigramMap;
    }

    public static String generateTigramText(final Map<String, List<String>> tigramMap) {

        if (tigramMap == null || tigramMap.isEmpty())
            throw new IllegalArgumentException("tigramMap must not be null or empty");

        final List<String> trigramKeys = new ArrayList<>(tigramMap.keySet());

        String nextWordKey = trigramKeys.get(generateRandomInt(trigramKeys.size() - 1));
        nextWordKey = StringUtils.camelCase(nextWordKey); // since this will be start of the sentence
        StringBuilder textBuffer = new StringBuilder(nextWordKey);
        List<String> nextWordsList = tigramMap.get(nextWordKey.toLowerCase());
        int sentenceLength = 2;
        int noOfSentences = 0;

        while (nextWordsList != null) {
            String randomNextWord;

            if (hasOnlyASingleEntry(nextWordsList)) {
                randomNextWord = nextWordsList.get(0);
            } else {
                randomNextWord = nextWordsList.get(generateRandomInt(nextWordsList.size() - 1));
            }

            String nextWordLookupValue = nextWordKey + " " + randomNextWord;
            sentenceLength++;
            String previousWord = StringUtils.getPreviousWord(nextWordKey);
            String capitaliseWord = StringUtils.capitaliseWord(previousWord, randomNextWord, sentenceLength);

            if (StringUtils.isNewSentence(sentenceLength, capitaliseWord)) {
                textBuffer.append(" ").append(capitaliseWord).append(".  ");
                sentenceLength = 0;
                noOfSentences++;
                if (StringUtils.isNewParagraph(noOfSentences)) {
                    textBuffer.append(System.lineSeparator());
                    textBuffer.append(System.lineSeparator());
                    noOfSentences = 0;
                }
            } else {
                if (StringUtils.isNewSentence(sentenceLength)) {
                    textBuffer.append(capitaliseWord);
                } else {
                    textBuffer.append(" ").append(capitaliseWord);
                }
            }

            nextWordKey = nextWordLookupValue.substring(nextWordLookupValue.indexOf(" ") + 1);
            nextWordsList = tigramMap.get(nextWordKey.toLowerCase());
        }
        return textBuffer.append(".").toString();
    }

    public static int generateRandomInt(int upperRange) {
        Random random = new Random();
        return random.nextInt(upperRange + 1);
    }

    public static int generateRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static Map<String, List<String>> generateFirstTigram(final String[] textArray) {

        Map<String, List<String>> tigramMap = new HashMap<>();

        String key = generateKey(textArray, 0);
        String value = generateValue(textArray, 0);

        addTigramKeyValue(tigramMap, key, value);

        return tigramMap;
    }

    private static String generateKey(final String[] textArray, final int index) {
        return textArray[index] + " " + textArray[index + 1];
    }

    private static String generateValue(final String[] textArray, final int index) {
        return textArray[index + 2];
    }

    private static void addTigramKeyValue(final Map<String, List<String>> tigramMap, final String key,
                    final String value) {
        List<String> values = new ArrayList<>();
        values.add(value);
        tigramMap.put(key, values);
    }

    private static boolean hasOnlyASingleEntry(List<String> nextWordsList) {
        return nextWordsList.size() == 1;
    }

}
