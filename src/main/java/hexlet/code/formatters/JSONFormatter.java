package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JSONFormatter {

    public static String format(Map<String, List<Object>> diffMap) {
        try {
            return new ObjectMapper().writeValueAsString(diffMap);
        } catch (JsonProcessingException e) {
            System.err.println("There was a unknown problem with JSON generation");
            throw new RuntimeException(e);
        }
    }
}
