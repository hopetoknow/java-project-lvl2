package hexlet.code;

import hexlet.code.exceptions.WrongFileFormatException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.DiffSigns.MINUS;
import static hexlet.code.DiffSigns.PLUS;
import static hexlet.code.DiffSigns.SPACE;

public class Differ {

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException,
            WrongFileFormatException {
        Map<String, Object> firstMap = Parser.parse(filePath1);
        Map<String, Object> secondMap = Parser.parse(filePath2);
        Map<String, List<Object>> diffMap = new TreeMap<>();

        Set<String> keys = new TreeSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());

        for (String key: keys) {
            if (!firstMap.containsKey(key)) {
                diffMap.put(key, Arrays.asList(secondMap.get(key), PLUS));
            } else if (!secondMap.containsKey(key)) {
                diffMap.put(key, Arrays.asList(firstMap.get(key), MINUS));
            } else if (Objects.equals(firstMap.get(key), secondMap.get(key))) {
                diffMap.put(key, Arrays.asList(firstMap.get(key), SPACE));
            } else {
                diffMap.put(key, Arrays.asList(firstMap.get(key), secondMap.get(key)));
            }
        }
        return Formatter.format(diffMap, formatName);
    }
}
