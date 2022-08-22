package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    private static final String PLUS = " + ";
    private static final String MINUS = " - ";
    private static final String SPACE = "   ";

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> firstJson = getJsonFromFile(filePath1);
        Map<String, Object> secondJson = getJsonFromFile(filePath2);

        Set<String> keys = new TreeSet<>(firstJson.keySet());
        keys.addAll(secondJson.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (String key: keys) {
            if (!firstJson.containsKey(key)) {
                appendToResult(secondJson, result, key, PLUS);
            } else {
                if (!secondJson.containsKey(key)) {
                    appendToResult(firstJson, result, key, MINUS);
                } else {
                    if (firstJson.get(key).equals(secondJson.get(key))) {
                        appendToResult(firstJson, result, key, SPACE);
                    } else {
                        appendToResult(firstJson, result, key, MINUS);
                        result.append("\n");
                        appendToResult(secondJson, result, key, PLUS);
                    }
                }
            }
            result.append("\n");
        }
        result.append("}");
        return result.toString();
    }
    private static Map<String, Object> getJsonFromFile(String filePath) throws IOException {
        String jsonAsString = Files.readString(Paths.get(filePath));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonAsString, new TypeReference<>() { });
    }

    private static void appendToResult(Map<String, Object> json, StringBuilder result, String key, String diffSign) {
        result.append(diffSign)
                .append(key)
                .append(": ")
                .append(json.get(key));
    }
}
