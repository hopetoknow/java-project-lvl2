package hexlet.code.formatters;

import hexlet.code.Formatter;
import hexlet.code.TestHelper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.DiffSigns.MINUS;
import static hexlet.code.DiffSigns.PLUS;
import static hexlet.code.DiffSigns.SPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StylishFormatterTest extends TestHelper {

    private static final String EXPECTED_STYLISH_DIFF_WHEN_SECOND_FILE_IS_EMPTY = """
                {
                  - chars1: [a, b, c]
                  - chars2: [d, e, f]
                  - checked: false
                  - default: null
                  - id: 45
                  - key1: value1
                  - numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  - numbers3: [3, 4, 5]
                  - setting1: Some value
                  - setting2: 200
                  - setting3: true
                }""";
    private static final String EXPECTED_STYLISH_DIFF_WHEN_BOTH_FILES_ARE_EMPTY = "{\n}";
    private static final Map<String, List<Object>> DIFF_MAP = new TreeMap<>();
    private static final Map<String, List<Object>> DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY = new TreeMap<>();
    private static final Map<String, List<Object>> DIFF_MAP_WHEN_BOTH_FILES_ARE_EMPTY = new TreeMap<>();

    @SuppressWarnings({"magicnumber", "indentation"})
    @BeforeAll
    public static void fillDiffMaps() {
        DIFF_MAP.put("chars1", Arrays.asList(Arrays.asList("a", "b", "c"), SPACE));
        DIFF_MAP.put("chars2", Arrays.asList(Arrays.asList("d", "e", "f"), false));
        DIFF_MAP.put("checked", Arrays.asList(false, true));
        DIFF_MAP.put("default", Arrays.asList(null, Arrays.asList("value1", "value2")));
        DIFF_MAP.put("id", Arrays.asList(45, null));
        DIFF_MAP.put("key1", Arrays.asList("value1", MINUS));
        DIFF_MAP.put("key2", Arrays.asList("value2", PLUS));
        DIFF_MAP.put("numbers1", Arrays.asList(Arrays.asList(1, 2, 3, 4), SPACE));
        DIFF_MAP.put("numbers2", Arrays.asList(Arrays.asList(2, 3, 4, 5), Arrays.asList(22, 33, 44, 55)));
        DIFF_MAP.put("numbers3", Arrays.asList(Arrays.asList(3, 4, 5), MINUS));
        DIFF_MAP.put("numbers4", Arrays.asList(Arrays.asList(4, 5, 6), PLUS));
        DIFF_MAP.put("obj1", Arrays.asList(new LinkedHashMap<>() {{
            put("nestedKey", "value");
            put("isNested", true);
        }}, PLUS));
        DIFF_MAP.put("setting1", Arrays.asList("Some value", "Another value"));
        DIFF_MAP.put("setting2", Arrays.asList(200, 300));
        DIFF_MAP.put("setting3", Arrays.asList(true, "none"));

        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("chars1", Arrays.asList(Arrays.asList("a", "b", "c"), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("chars2", Arrays.asList(Arrays.asList("d", "e", "f"), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("checked", Arrays.asList(false, MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("default", Arrays.asList(null, MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("id", Arrays.asList(45, MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("key1", Arrays.asList("value1", MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("numbers1", Arrays.asList(Arrays.asList(1, 2, 3, 4), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("numbers2", Arrays.asList(Arrays.asList(2, 3, 4, 5), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("numbers3", Arrays.asList(Arrays.asList(3, 4, 5), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("setting1", Arrays.asList("Some value", MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("setting2", Arrays.asList(200, MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("setting3", Arrays.asList(true, MINUS));
    }

    @Test
    public void format() {
        String actualDiff = Formatter.format(DIFF_MAP, FormatNames.stylishFormatName.getFilename());
        assertEquals(EXPECTED_STYLISH_DIFF, actualDiff);
    }

    @Test
    public void formatWhenSecondFileIsEmpty() {
        String actualDiff = Formatter.format(DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY,
                FormatNames.stylishFormatName.getFilename());
        assertEquals(EXPECTED_STYLISH_DIFF_WHEN_SECOND_FILE_IS_EMPTY, actualDiff);
    }

    @Test
    public void formatWhenBothFileAreEmpty() {
        String actualDiff = Formatter.format(DIFF_MAP_WHEN_BOTH_FILES_ARE_EMPTY,
                FormatNames.stylishFormatName.getFilename());
        assertEquals(EXPECTED_STYLISH_DIFF_WHEN_BOTH_FILES_ARE_EMPTY, actualDiff);
    }
}
