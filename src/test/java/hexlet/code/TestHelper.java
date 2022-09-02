package hexlet.code;

import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static hexlet.code.DiffSigns.MINUS;
import static hexlet.code.DiffSigns.PLUS;
import static hexlet.code.DiffSigns.SPACE;

public class TestHelper {

    public static final String EXPECTED_STYLISH_DIFF = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

    public static final String EXPECTED_PLAIN_DIFF = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";

    public static final String EXPECTED_JSON_DIFF = "{\"chars1\":[[\"a\",\"b\",\"c\"],\"SPACE\"],\"chars2\":"
            + "[[\"d\",\"e\",\"f\"],false],\"checked\":[false,true],\"default\":[null,[\"value1\",\"value2\"]],\"id\":"
            + "[45,null],\"key1\":[\"value1\",\"MINUS\"],\"key2\":[\"value2\",\"PLUS\"],\"numbers1\":[[1,2,3,4],"
            + "\"SPACE\"],\"numbers2\":[[2,3,4,5],[22,33,44,55]],\"numbers3\":[[3,4,5],\"MINUS\"],\"numbers4\":"
            + "[[4,5,6],\"PLUS\"],\"obj1\":[{\"nestedKey\":\"value\",\"isNested\":true},\"PLUS\"],\"setting1\":"
            + "[\"Some value\",\"Another value\"],\"setting2\":[200,300],\"setting3\":[true,\"none\"]}";

    public static final Map<String, List<Object>> DIFF_MAP = new TreeMap<>();

    public static final Map<String, List<Object>> DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY = new TreeMap<>();

    public static final Map<String, List<Object>> DIFF_MAP_WHEN_BOTH_FILES_ARE_EMPTY = new TreeMap<>();

    public static final Integer THREE = 3;
    public static final Integer FOUR = 4;
    public static final Integer FIVE = 5;
    public static final Integer SIX = 6;
    public static final Integer TWENTY_TWO = 22;
    public static final Integer THIRTY_THREE = 33;
    public static final Integer FORTY_FOUR = 44;
    public static final Integer FORTY_FIVE = 45;
    public static final Integer FIFTY_FIVE = 55;
    public static final Integer TWO_HUNDRED = 200;
    public static final Integer THREE_HUNDRED = 300;

    @BeforeAll
    public static void fillDiffMaps() {
        DIFF_MAP.put("chars1", Arrays.asList(Arrays.asList("a", "b", "c"), SPACE));
        DIFF_MAP.put("chars2", Arrays.asList(Arrays.asList("d", "e", "f"), false));
        DIFF_MAP.put("checked", Arrays.asList(false, true));
        DIFF_MAP.put("default", Arrays.asList(null, Arrays.asList("value1", "value2")));
        DIFF_MAP.put("id", Arrays.asList(FORTY_FIVE, null));
        DIFF_MAP.put("key1", Arrays.asList("value1", MINUS));
        DIFF_MAP.put("key2", Arrays.asList("value2", PLUS));
        DIFF_MAP.put("numbers1", Arrays.asList(Arrays.asList(1, 2, THREE, FOUR), SPACE));
        DIFF_MAP.put("numbers2", Arrays.asList(Arrays.asList(2, THREE, FOUR, FIVE),
                Arrays.asList(TWENTY_TWO, THIRTY_THREE, FORTY_FOUR, FIFTY_FIVE)));
        DIFF_MAP.put("numbers3", Arrays.asList(Arrays.asList(THREE, FOUR, FIVE), MINUS));
        DIFF_MAP.put("numbers4", Arrays.asList(Arrays.asList(FOUR, FIVE, SIX), PLUS));
        DIFF_MAP.put("obj1", Arrays.asList(new LinkedHashMap<>() {{
                put("nestedKey", "value");
                put("isNested", true);
            }}, PLUS));
        DIFF_MAP.put("setting1", Arrays.asList("Some value", "Another value"));
        DIFF_MAP.put("setting2", Arrays.asList(TWO_HUNDRED, THREE_HUNDRED));
        DIFF_MAP.put("setting3", Arrays.asList(true, "none"));

        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("chars1", Arrays.asList(Arrays.asList("a", "b", "c"), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("chars2", Arrays.asList(Arrays.asList("d", "e", "f"), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("checked", Arrays.asList(false, MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("default", Arrays.asList(null, MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("id", Arrays.asList(FORTY_FIVE, MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("key1", Arrays.asList("value1", MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("numbers1", Arrays.asList(Arrays.asList(1, 2, THREE, FOUR), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("numbers2", Arrays.asList(Arrays.asList(2, THREE, FOUR, FIVE), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("numbers3", Arrays.asList(Arrays.asList(THREE, FOUR, FIVE), MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("setting1", Arrays.asList("Some value", MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("setting2", Arrays.asList(TWO_HUNDRED, MINUS));
        DIFF_MAP_WHEN_SECOND_FILE_IS_EMPTY.put("setting3", Arrays.asList(true, MINUS));
    }

    /**
     * @param name of the file
     * @return path to the file
     */
    public String getPathByFilename(final String name) {
        return new File(getClass().getClassLoader().getResource(name).getFile()).getPath();
    }

    public enum Filenames {
        firstJSONFilename("file1.json"), secondJSONFilename("file2.json"), firstYAMLFilename("file1.yml"),
        secondYAMLFilename("file2.yml"), wrongFormatFilename("file.doc");

        private final String filename;

        Filenames(String name) {
            this.filename = name;
        }

        public String getFilename() {
            return filename;
        }
    }

    public enum FormatNames {
        stylishFormatName("stylish"), plainFormatName("plain"), JSONFormatName("json");
        private final String formatName;

        FormatNames(String name) {
            this.formatName = name;
        }

        public String getFilename() {
            return formatName;
        }
    }
}

