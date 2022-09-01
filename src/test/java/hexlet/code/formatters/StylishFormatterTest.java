package hexlet.code.formatters;

import hexlet.code.Formatter;
import hexlet.code.TestHelper;
import org.junit.jupiter.api.Test;

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
    public void formatWhenBothFilesAreEmpty() {
        String actualDiff = Formatter.format(DIFF_MAP_WHEN_BOTH_FILES_ARE_EMPTY,
                FormatNames.stylishFormatName.getFilename());
        assertEquals(EXPECTED_STYLISH_DIFF_WHEN_BOTH_FILES_ARE_EMPTY, actualDiff);
    }
}
