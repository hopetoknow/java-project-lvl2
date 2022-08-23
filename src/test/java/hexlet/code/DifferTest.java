package hexlet.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DifferTest {

    @Test
    public void generatePositive() throws Exception {
        String expectedDiff = """
                {
                 - follow: false
                   host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 + timeout: 20
                 + verbose: true
                }""";

        String actualDiff = Differ.generate(getPathByFilename("file1.json"), getPathByFilename("file2.json"));
        assertEquals(expectedDiff, actualDiff);
    }

    @Test
    public void generateNegative() throws Exception {
        String incorrectDiff = """
                {
                 - follow: false
                 - host: hexlet.io
                 - proxy: 123.234.53.22
                 - timeout: 50
                 - timeout: 20
                 - verbose: true
                }""";

        String actualDiff = Differ.generate(getPathByFilename("file1.json"), getPathByFilename("file2.json"));
        assertNotEquals(incorrectDiff, actualDiff);
    }

    @Test
    public void generateWhenExceptionOccurs() {
        IOException thrown = Assertions.assertThrows(IOException.class, () ->
                Differ.generate(getPathByFilename("file1.json"), getPathByFilename("file2.json") + "42"));

        Assertions.assertEquals(getPathByFilename("file2.json") + "42", thrown.getMessage());
    }

    private String getPathByFilename(final String name) {
        return new File(getClass().getClassLoader().getResource(name).getFile()).getPath();
    }
}
