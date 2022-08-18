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
        String json1 = Files.readString(Paths.get(filePath1));
        String json2 = Files.readString(Paths.get(filePath2));

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> jsonAsMap1 = objectMapper.readValue(json1, new TypeReference<>(){});
        Map<String, Object> jsonAsMap2 = objectMapper.readValue(json2, new TypeReference<>(){});

        Set<String> keys = new TreeSet<>(jsonAsMap1.keySet());
        keys.addAll(jsonAsMap2.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (String key: keys) {
            if (!jsonAsMap1.containsKey(key)) {
                result.append(" + ")
                        .append(key)
                        .append(": ")
                        .append(jsonAsMap2.get(key));
            } else {
                if (!jsonAsMap2.containsKey(key)) {
                    result.append(" - ")
                            .append(key)
                            .append(": ")
                            .append(jsonAsMap1.get(key));
                } else {
                    if (jsonAsMap1.get(key).equals(jsonAsMap2.get(key))) {
                        result.append("   ")
                                .append(key)
                                .append(": ")
                                .append(jsonAsMap1.get(key));
                    } else {
                        result.append(" - ")
                                .append(key)
                                .append(": ")
                                .append(jsonAsMap1.get(key))
                                .append("\n");
                        result.append(" + ")
                                .append(key)
                                .append(": ")
                                .append(jsonAsMap2.get(key));
                    }
                }
            }
            result.append("\n");
        }
        result.append("}");
        return result.toString();
    }
}
