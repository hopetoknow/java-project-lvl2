package hexlet.code;

import hexlet.code.exceptions.WrongFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import static hexlet.code.TestHelper.Filenames.firstJSONFilename;
import static hexlet.code.TestHelper.Filenames.firstYAMLFilename;
import static hexlet.code.TestHelper.Filenames.secondJSONFilename;
import static hexlet.code.TestHelper.Filenames.wrongFormatFilename;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest extends TestHelper {

    private static final Map<String, Object> EXPECTED_MAP = new LinkedHashMap<>();

    @BeforeAll
    public static void fillExpectedMap() {
        EXPECTED_MAP.put("setting1", "Some value");
        EXPECTED_MAP.put("setting2", TWO_HUNDRED);
        EXPECTED_MAP.put("setting3", true);
        EXPECTED_MAP.put("key1", "value1");
        EXPECTED_MAP.put("numbers1", Arrays.asList(1, 2, THREE, FOUR));
        EXPECTED_MAP.put("numbers2", Arrays.asList(2, THREE, FOUR, FIVE));
        EXPECTED_MAP.put("id", FORTY_FIVE);
        EXPECTED_MAP.put("default", null);
        EXPECTED_MAP.put("checked", false);
        EXPECTED_MAP.put("numbers3", Arrays.asList(THREE, FOUR, FIVE));
        EXPECTED_MAP.put("chars1", Arrays.asList("a", "b", "c"));
        EXPECTED_MAP.put("chars2", Arrays.asList("d", "e", "f"));
    }

    @Test
    public void parseJSON() throws Exception {
        Map<String, Object> actualMap = Parser.parse(getPathByFilename(firstJSONFilename.getFilename()));
        assertEquals(EXPECTED_MAP, actualMap);
    }

    @Test
    public void parseYAML() throws Exception {
        Map<String, Object> actualMap = Parser.parse(getPathByFilename(firstYAMLFilename.getFilename()));
        assertEquals(EXPECTED_MAP, actualMap);
    }

    @Test
    public void parseWhenIncorrectFilepath() {
        IOException thrown = Assertions.assertThrows(IOException.class, () ->
                Parser.parse(getPathByFilename(secondJSONFilename.getFilename()) + "42"));
        assertEquals(getPathByFilename(secondJSONFilename.getFilename()) + "42",
                thrown.getMessage());
    }

    @Test
    public void parseWhenWrongFileFormat() {
        WrongFileFormatException thrown = Assertions.assertThrows(WrongFileFormatException.class, () ->
                Parser.parse(getPathByFilename(wrongFormatFilename.getFilename())));
        assertEquals("This format file is not supported. Run app with -h to get help.",
                thrown.getMessage());
    }
}
