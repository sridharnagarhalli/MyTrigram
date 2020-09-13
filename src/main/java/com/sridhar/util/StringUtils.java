package com.sridhar.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class StringUtils {

    public static final String SPACE = " ";

    private static final List<String> NOUNS =
                    Arrays.asList("i", "mr", "mrs", "miss", "dr", "tom", "swift", "sam", "andy", "pete", "snedecker",
                                    "barton", "shopton", "new", "york", "baggert", "garret", "jackson", "damon",
                                    "waterford", "carlopa", "tod", "boreck", "murdock", "boomerang", "ned", "newton",
                                    "mary", "nestor", "foger", "john", "sharp");

    private StringUtils() {
        // private method for utility class
    }

    public static String removePunctuation(final String string) {
        return string.replaceAll("\\p{P}", "");
    }

    public static String removeNewLine(final String string) {
        return string.replaceAll("\n", " ").replaceAll("\r", " ");
    }

    public static String removeWhiteSpace(final String string) {
        return string.trim().replaceAll(" +", " ");
    }

    public static String[] splitString(final String string, final String regex) {
        if (Objects.isNull(regex)) {
            return string.split(SPACE);
        }
        return string.split(regex);
    }

    public static String camelCase(final String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static String getPreviousWord(String nextWordKey) {
        return splitString(nextWordKey, StringUtils.SPACE)[1];
    }

    public static String capitaliseWord(String previousWord, String word, int sentenceLength) {

        if (isNewSentence(sentenceLength)) {
            return StringUtils.camelCase(word);
        } else if (NOUNS.contains(word.toLowerCase())) {
            return StringUtils.camelCase(word);
        } else if (Arrays.asList("mr", "mrs", ".").contains(previousWord.toLowerCase())) {
            return StringUtils.camelCase(word);
        } else {
            return word;
        }
    }

    public static boolean isNewSentence(final int sentenceLength, final String nextWord) {

        switch (nextWord.toLowerCase()) {
            case "i":
            case "a":
            case "mr":
            case "mrs":
            case "and":
                return false;
            default:
                int goodSentenceLength = TrigramUtils.generateRandomInt(15, 20);
                return sentenceLength >= goodSentenceLength;
        }

    }

    public static boolean isNewSentence(int sentenceLength) {
        return sentenceLength == 1;
    }

    public static boolean isNewParagraph(int noOfSentences) {
        int goodParagraphLength = TrigramUtils.generateRandomInt(3, 6);
        return noOfSentences >= goodParagraphLength;
    }

}
