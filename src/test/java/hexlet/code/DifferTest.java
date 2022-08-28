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
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
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
                  - follow: false
                  - host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
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
