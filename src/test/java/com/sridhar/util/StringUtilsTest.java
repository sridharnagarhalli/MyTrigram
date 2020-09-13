package com.sridhar.util;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    @Test
    public void testRemovePunctuation() {
        assertEquals("Gracious goodness What was that Mrs Baggert", StringUtils.removePunctuation("\"Gracious goodness!\" What was that, Mrs. Baggert?"));
    }

    @Test
    public void testSplitStringBySpace(){
        assertArrayEquals(new String[]{"test", "test"}, StringUtils.splitString("test test", null));
        assertArrayEquals(new String[]{"Gracious", "goodness", "What", "was", "that", "Mrs", "Baggert"}, StringUtils.splitString("Gracious goodness What was that Mrs Baggert", null));
        assertArrayEquals(new String[]{"There", "was", "a", "rushing,", "whizzing,", "throbbing", "noise", "in", "the", "air.", "", "A", "great", "body,", "like", "that", "of", "some", "immense", "bird,", "sailed", "along,", "casting", "a", "grotesque"}, StringUtils
                        .splitString("There was a rushing, whizzing, throbbing noise in the air.  A great body, like that of some immense bird, sailed along, casting a grotesque", null));
    }

    @Test
    public void testRemoveNewLine(){
        assertEquals("this has no new lines in it", StringUtils.removeNewLine("this has no new lines\nin it"));
        assertEquals("this has no new lines in it", StringUtils.removeNewLine("this has no new lines\rin it"));
        assertEquals("this has no new lines  in it", StringUtils.removeNewLine("this has no new lines\n\rin it"));
    }

    @Test
    public void testRemoveWhiteSpace(){
        assertEquals("whitespace removed", StringUtils.removeWhiteSpace("                 whitespace removed                "));
        assertEquals("whitespace removed", StringUtils.removeWhiteSpace("                 whitespace                   removed                "));
        assertEquals("whitespace removed", StringUtils.removeWhiteSpace("whitespace                   removed"));
    }

    @Test
    public void testCapitaliseCamelCase(){
        assertEquals("Hello", StringUtils.camelCase("hello"));
        assertEquals("Another hello", StringUtils.camelCase("another hello"));
        assertEquals("Hello", StringUtils.camelCase("Hello"));
    }

    @Test
    public void testCapitaliseWord() {
        assertEquals("I", StringUtils.capitaliseWord("prevWord", "i", 2));
        assertEquals("Mr", StringUtils.capitaliseWord("prevWord", "mr", 2));
        assertEquals("Mrs", StringUtils.capitaliseWord("prevWord", "mrs", 2));
        assertEquals("Tom", StringUtils.capitaliseWord("prevWord", "tom", 2));
        assertEquals("I", StringUtils.capitaliseWord("prevWord", "i", 2));
    }

    @Test
    public void testCapitaliseWordForTheFirstWord() {
        assertEquals("I", StringUtils.capitaliseWord("prevWord", "i", 1));
        assertEquals("Now", StringUtils.capitaliseWord("prevWord", "now", 1));
        assertEquals("Not", StringUtils.capitaliseWord("prevWord", "not", 1));
        assertEquals("A", StringUtils.capitaliseWord("prevWord", "a", 1));
        assertEquals("Gibberish", StringUtils.capitaliseWord("prevWord", "gibberish", 1));
    }

    @Test
    public void testCapitaliseWordForThePreviousWord() {
        assertEquals("Smith", StringUtils.capitaliseWord("mr", "smith", 2));
        assertEquals("Tiggiewinkle", StringUtils.capitaliseWord("mrs", "tiggiewinkle", 2));
        assertEquals("Dot", StringUtils.capitaliseWord(".", "dot", 2));
    }

    @Test
    public void testIsNewSentence() {
        assertTrue(StringUtils.isNewSentence(1));
        assertFalse(StringUtils.isNewSentence(0));
        assertFalse(StringUtils.isNewSentence(2));
        assertFalse(StringUtils.isNewSentence(100));
    }

    @Test
    public void testIsNewSentenceWithNextWord() {
        assertTrue(StringUtils.isNewSentence(20, "another"));
        assertTrue(StringUtils.isNewSentence(20, "word"));
        assertTrue(StringUtils.isNewSentence(20, "altogether"));

        assertFalse(StringUtils.isNewSentence(20, "i"));
        assertFalse(StringUtils.isNewSentence(20, "a"));
        assertFalse(StringUtils.isNewSentence(20, "mr"));
        assertFalse(StringUtils.isNewSentence(20, "mrs"));
        assertFalse(StringUtils.isNewSentence(20, "and"));

        assertFalse(StringUtils.isNewSentence(14, "another"));
        assertFalse(StringUtils.isNewSentence(10, "word"));
        assertFalse(StringUtils.isNewSentence(14, "altogether"));
    }

    @Test
    public void testIsNewParagraph() {
        assertTrue(StringUtils.isNewParagraph(6));
        assertTrue(StringUtils.isNewParagraph(7));
        assertTrue(StringUtils.isNewParagraph(TrigramUtils.generateRandomInt(6, 1000)));

        assertFalse(StringUtils.isNewParagraph(2));
        assertFalse(StringUtils.isNewParagraph(1));
    }

}