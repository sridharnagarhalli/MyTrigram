package com.sridhar.util;

import com.sridhar.logging.Logger;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class TrigramUtilsTest {

    final String[] textArray = new String[]{"i", "wish", "i", "may", "i", "wish", "i", "might"};

    @Test
    public void testGenerateTigramMap() {
        Map<String, List<String>> tigramMap = TrigramUtils.generateTigramMap(textArray);
        Logger.log(tigramMap.toString());
        assertTrue(tigramMap.containsKey("i wish"));
        List<String> valueList = tigramMap.get("i wish");
        assertEquals(2, valueList.size());
        assertEquals("i", valueList.get(0));
        assertEquals("i", valueList.get(1));

        assertTrue(tigramMap.containsKey("may i"));
        valueList = tigramMap.get("may i");
        assertEquals(1, valueList.size());
        assertEquals("wish", valueList.get(0));

        assertTrue(tigramMap.containsKey("i may"));
        valueList = tigramMap.get("i may");
        assertEquals(1, valueList.size());
        assertEquals("i", valueList.get(0));

        assertTrue(tigramMap.containsKey("wish i"));
        valueList = tigramMap.get("wish i");
        assertEquals(2, valueList.size());
        assertEquals("may", valueList.get(0));
        assertEquals("might", valueList.get(1));
    }

    @Test
    public void testGenerateTigramText() {
        Map<String, List<String>> tigramMap = TrigramUtils.generateTigramMap(textArray);
        Logger.log(tigramMap.toString());
        String text = TrigramUtils.generateTigramText(tigramMap);
        Logger.log(text);
        assertNotNull(text);
        assertTrue(text.length() > 0);

    }

}