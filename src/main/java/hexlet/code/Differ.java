package hexlet.code;

import hexlet.code.exceptions.WrongFileFormatException;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {

    private static final String PLUS = "  + ";
    private static final String MINUS = "  - ";
    private static final String SPACE = "    ";

    public static String generate(String filePath1, String filePath2) throws IOException, WrongFileFormatException {
        Map<String, Object> firstMap = Parser.parse(filePath1);
        Map<String, Object> secondMap = Parser.parse(filePath2);

        Set<String> keys = new TreeSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());

        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (String key: keys) {
            if (!firstMap.containsKey(key)) {
                appendToResult(secondMap, result, key, PLUS);
            } else if (!secondMap.containsKey(key)) {
                appendToResult(firstMap, result, key, MINUS);
            } else if (firstMap.get(key).equals(secondMap.get(key))) {
                appendToResult(firstMap, result, key, SPACE);
            } else {
                appendToResult(firstMap, result, key, MINUS);
                result.append("\n");
                appendToResult(secondMap, result, key, PLUS);
            }
            result.append("\n");
        }
        return result.append("}").toString();
    }

    private static void appendToResult(Map<String, Object> json, StringBuilder result, String key, String diffSign) {
        result.append(diffSign)
                .append(key)
                .append(": ")
                .append(json.get(key));
    }
}
