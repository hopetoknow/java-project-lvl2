package hexlet.code.formatters;

import hexlet.code.Formatter;
import hexlet.code.TestHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlainFormatterTest extends TestHelper {

    private static final String EXPECTED_PLAIN_DIFF_WHEN_SECOND_FILE_IS_EMPTY = """
            Property 'chars1' was removed
            Property 'chars2' was removed
            Property 'checked' was removed
            Property 'default' was removed
            Property 'id' was removed
            Property 'key1' was removed
            Property 'numbers1' was removed
            Property 'numbers2' was removed
            Property 'numbers3' was removed
            Property 'setting1' was removed
            Property 'setting2' was removed
            Property 'setting3' was removed""";

    private static final String EXPECTED_PLAIN_DIFF_WHEN_BOTH_FILES_ARE_EMPTY = "";

    @Test
    public void format() {
        String actualDiff = Formatter.format(DIFF_MAP, FormatNames.plainFormatName.getFilename());
        assertEquals(EXPECTED_PLAIN_DIFF, actualDiff);
    }

    @Test
    public void formatWhenSecondFileIsEmpty() {
        String actualDiff = Formatter.format(DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY,
                FormatNames.plainFormatName.getFilename());
        assertEquals(EXPECTED_PLAIN_DIFF_WHEN_SECOND_FILE_IS_EMPTY, actualDiff);
    }

    @Test
    public void formatWhenBothFilesAreEmpty() {
        String actualDiff = Formatter.format(DIFF_MAP_WHEN_BOTH_FILES_ARE_EMPTY,
                FormatNames.plainFormatName.getFilename());
        assertEquals(EXPECTED_PLAIN_DIFF_WHEN_BOTH_FILES_ARE_EMPTY, actualDiff);
    }
}
