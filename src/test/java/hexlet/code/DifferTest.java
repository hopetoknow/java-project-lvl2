package hexlet.code;

import org.junit.jupiter.api.Test;

import static hexlet.code.TestHelper.Filenames.firstJSONFilename;
import static hexlet.code.TestHelper.Filenames.firstYAMLFilename;
import static hexlet.code.TestHelper.Filenames.secondJSONFilename;
import static hexlet.code.TestHelper.Filenames.secondYAMLFilename;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest extends TestHelper {

    @Test
    public void generateJSONWhenStylish() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(firstJSONFilename.getFilename()),
                getPathByFilename(secondJSONFilename.getFilename()), FormatNames.stylishFormatName.getFilename());
        assertEquals(EXPECTED_STYLISH_DIFF, actualDiff);
    }

    @Test
    public void generateYAMLWhenStylish() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(firstYAMLFilename.getFilename()),
                getPathByFilename(secondYAMLFilename.getFilename()), FormatNames.stylishFormatName.getFilename());
        assertEquals(EXPECTED_STYLISH_DIFF, actualDiff);
    }

    @Test
    public void generateJSONWhenPlain() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(firstJSONFilename.getFilename()),
                getPathByFilename(secondJSONFilename.getFilename()), FormatNames.plainFormatName.getFilename());
        assertEquals(EXPECTED_PLAIN_DIFF, actualDiff);
    }

    @Test
    public void generateYAMLWhenPlain() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename(firstYAMLFilename.getFilename()),
                getPathByFilename(secondYAMLFilename.getFilename()), FormatNames.plainFormatName.getFilename());
        assertEquals(EXPECTED_PLAIN_DIFF, actualDiff);
    }
}
