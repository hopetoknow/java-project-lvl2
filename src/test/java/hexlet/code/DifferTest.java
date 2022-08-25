package hexlet.code;

import hexlet.code.exceptions.WrongFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DifferTest {

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
        String actualDiff = Differ.generate(getPathByFilename("file1.json"), getPathByFilename("file2.json"));
        assertEquals(expectedDiff, actualDiff);
    }

    @Test
    public void generateNegativeWhenJSON() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename("file1.json"), getPathByFilename("file2.json"));
        assertNotEquals(incorrectDiff, actualDiff);
    }
    @Test
    public void generatePositiveWhenYAML() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename("file1.yml"), getPathByFilename("file2.yml"));
        assertEquals(expectedDiff, actualDiff);
    }

    @Test
    public void generateNegativeWhenYAML() throws Exception {
        String actualDiff = Differ.generate(getPathByFilename("file1.yml"), getPathByFilename("file2.yml"));
        assertNotEquals(incorrectDiff, actualDiff);
    }

    @Test
    public void generateWhenExceptionOccurs() {
        IOException thrown = Assertions.assertThrows(IOException.class, () ->
                Differ.generate(getPathByFilename("file1.json"), getPathByFilename("file2.json") + "42"));

        Assertions.assertEquals(getPathByFilename("file2.json") + "42", thrown.getMessage());
    }

    @Test
    public void generateWhenWrongFileFormat() {
        WrongFileFormatException thrown = Assertions.assertThrows(WrongFileFormatException.class, () ->
                Differ.generate(getPathByFilename("file1.json"), getPathByFilename("file.doc")));

        Assertions.assertEquals("This format file is not supported. Run app with -h to get help.",
                thrown.getMessage());
    }

    private String getPathByFilename(final String name) {
        return new File(getClass().getClassLoader().getResource(name).getFile()).getPath();
    }
}
