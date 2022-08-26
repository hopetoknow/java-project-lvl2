package hexlet.code;

import hexlet.code.exceptions.WrongFileFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.TestHelper.Filenames.secondJSONFilename;
import static hexlet.code.TestHelper.Filenames.wrongFormatFilename;

public class ParserTest extends TestHelper {

    @Test
    public void parseWhenExceptionOccurs() {
        IOException thrown = Assertions.assertThrows(IOException.class, () ->
                Parser.parse(getPathByFilename(secondJSONFilename.getFilename()) + "42"));

        Assertions.assertEquals(getPathByFilename(secondJSONFilename.getFilename()) + "42",
                thrown.getMessage());
    }

    @Test
    public void parseWhenWrongFileFormat() {
        WrongFileFormatException thrown = Assertions.assertThrows(WrongFileFormatException.class, () ->
                Parser.parse(getPathByFilename(wrongFormatFilename.getFilename())));

        Assertions.assertEquals("This format file is not supported. Run app with -h to get help.",
                thrown.getMessage());
    }
}
