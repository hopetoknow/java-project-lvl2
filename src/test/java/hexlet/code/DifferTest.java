package hexlet.code;

import org.junit.jupiter.api.Test;

import static hexlet.code.TestHelper.Filenames.firstJSONFilename;
import static hexlet.code.TestHelper.Filenames.secondJSONFilename;
import static hexlet.code.TestHelper.Filenames.firstYAMLFilename;
import static hexlet.code.TestHelper.Filenames.secondYAMLFilename;
import static hexlet.code.TestHelper.Filenames.emptyJSONFileName;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DifferTest extends TestHelper {

    private final String expectedDiff = """
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

    private final String incorrectDiff = """
                {
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  - timeout: 20
                  - verbose: true
                }""";

    @Test
    public void generatePositiveWhenJSON() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(firstJSONFilename.getFilename()),
                getPathByFilename(secondJSONFilename.getFilename()));
        assertEquals(expectedDiff, actualDiff);
    }

    @Test
    public void generateNegativeWhenJSON() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(firstJSONFilename.getFilename()),
                getPathByFilename(secondJSONFilename.getFilename()));
        assertNotEquals(incorrectDiff, actualDiff);
    }

    @Test
    public void generatePositiveWhenYAML() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(firstYAMLFilename.getFilename()),
                getPathByFilename(secondYAMLFilename.getFilename()));
        assertEquals(expectedDiff, actualDiff);
    }

    @Test
    public void generateNegativeWhenYAML() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(firstYAMLFilename.getFilename()),
                getPathByFilename(secondYAMLFilename.getFilename()));
        assertNotEquals(incorrectDiff, actualDiff);
    }

    @Test
    public void generatePositiveWhenOneFileIsEmpty() throws Exception {
        String expectedDiffWhenOneFileIsEmpty = """
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
        String actualDiff = Differ.generate(getPathByFilename(firstJSONFilename.getFilename()),
                getPathByFilename(emptyJSONFileName.getFilename()));
        assertEquals(expectedDiffWhenOneFileIsEmpty, actualDiff);
    }

    @Test
    public void generatePositiveWhenBothFilesAreEmpty() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(emptyJSONFileName.getFilename()),
                getPathByFilename(emptyJSONFileName.getFilename()));
        assertEquals("{\n}", actualDiff);
    }
}
