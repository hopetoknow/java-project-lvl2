package hexlet.code;

import java.util.List;
import java.util.Map;

import static hexlet.code.DiffSigns.MINUS;
import static hexlet.code.DiffSigns.PLUS;

public class StylishFormatter {

    public static String format(Map<String, List<Object>> diffMap) {
        StringBuilder result = new StringBuilder();
        result.append("{\n");
        for (String key: diffMap.keySet()) {
            Object sign = diffMap.get(key).get(1);
            if (!(sign instanceof DiffSigns diffSign)) {
                result.append(MINUS.getDiffSign())
                        .append(key)
                        .append(": ")
                        .append(diffMap.get(key).get(0))
                        .append("\n")
                        .append(PLUS.getDiffSign())
                        .append(key)
                        .append(": ")
                        .append(diffMap.get(key).get(1));
            } else {
                result.append(diffSign.getDiffSign())
                        .append(key)
                        .append(": ")
                        .append(diffMap.get(key).get(0));
            }
            result.append("\n");
        }
        return result.append("}").toString();
    }
}
