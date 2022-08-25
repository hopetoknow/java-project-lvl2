package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hexlet.code.exceptions.WrongFileFormatException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    private static final String JSON = "json";
    private static final String YAML = "yml";

    public static Map<String, Object> parse(String filePath) throws IOException, WrongFileFormatException {
        String dataAsString = Files.readString(Paths.get(filePath));
        String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
        return getMapper(fileExtension).readValue(dataAsString, new TypeReference<>() { });
    }

    private static ObjectMapper getMapper(String fileExtension) throws WrongFileFormatException {
        ObjectMapper objectMapper;
        if (fileExtension.equals(JSON)) {
            objectMapper = new ObjectMapper();
        } else if (fileExtension.equals(YAML)) {
            objectMapper =  new ObjectMapper(new YAMLFactory());
        } else {
            throw new WrongFileFormatException("This format file is not supported. Run app with -h to get help.");
        }
        return objectMapper;
    }
}
