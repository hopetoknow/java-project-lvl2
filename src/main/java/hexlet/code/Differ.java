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

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> firstJson = getJsonFromFile(filePath1);
        Map<String, Object> secondJson = getJsonFromFile(filePath2);

        Set<String> keys = new TreeSet<>(firstJson.keySet());
        keys.addAll(secondJson.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (String key: keys) {
            if (!firstJson.containsKey(key)) {
                result.append(" + ")
                        .append(key)
                        .append(": ")
                        .append(secondJson.get(key));
            } else {
                if (!secondJson.containsKey(key)) {
                    result.append(" - ")
                            .append(key)
                            .append(": ")
                            .append(firstJson.get(key));
                } else {
                    if (firstJson.get(key).equals(secondJson.get(key))) {
                        result.append("   ")
                                .append(key)
                                .append(": ")
                                .append(firstJson.get(key));
                    } else {
                        result.append(" - ").append(key).append(": ").append(firstJson.get(key)).append("\n");
                        result.append(" + ")
                                .append(key)
                                .append(": ")
                                .append(secondJson.get(key));
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
        return objectMapper.readValue(jsonAsString, new TypeReference<>(){});
    }
}
