package hexlet.code.formatters;

import hexlet.code.Formatter;
import hexlet.code.TestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONFormatterTest extends TestHelper {

    private static final String EXPECTED_JSON_DIFF_WHEN_SECOND_FILE_IS_EMPTY = "{\"chars1\":[[\"a\",\"b\",\"c\"],"
            + "\"MINUS\"],\"chars2\":[[\"d\",\"e\",\"f\"],\"MINUS\"],\"checked\":[false,\"MINUS\"],\"default\":"
            + "[null,\"MINUS\"],\"id\":[45,\"MINUS\"],\"key1\":[\"value1\",\"MINUS\"],\"numbers1\":[[1,2,3,4],"
            + "\"MINUS\"],\"numbers2\":[[2,3,4,5],\"MINUS\"],\"numbers3\":[[3,4,5],\"MINUS\"],\"setting1\":"
            + "[\"Some value\",\"MINUS\"],\"setting2\":[200,\"MINUS\"],\"setting3\":[true,\"MINUS\"]}";

    private static final String EXPECTED_JSON_DIFF_WHEN_BOTH_FILES_ARE_EMPTY = "{}";

    @Test
    public void format() {
        String actualDiff = Formatter.format(DIFF_MAP, FormatNames.JSONFormatName.getFilename());
        assertEquals(EXPECTED_JSON_DIFF, actualDiff);
    }

    @Test
    public void formatWhenSecondFileIsEmpty() {
        String actualDiff = Formatter.format(DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY,
                FormatNames.JSONFormatName.getFilename());
        assertEquals(EXPECTED_JSON_DIFF_WHEN_SECOND_FILE_IS_EMPTY, actualDiff);
    }

    @Test
    public void formatWhenBothFilesAreEmpty() {
        String actualDiff = Formatter.format(DIFF_MAP_WHEN_BOTH_FILES_ARE_EMPTY,
                FormatNames.JSONFormatName.getFilename());
        assertEquals(EXPECTED_JSON_DIFF_WHEN_BOTH_FILES_ARE_EMPTY, actualDiff);
    }
}
