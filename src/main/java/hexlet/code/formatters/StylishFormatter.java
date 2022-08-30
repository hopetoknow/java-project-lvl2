package hexlet.code.formatters;

import hexlet.code.DiffSigns;

import java.util.List;
import java.util.Map;

import static hexlet.code.DiffSigns.MINUS;
import static hexlet.code.DiffSigns.PLUS;

public class StylishFormatter {

    public static String format(Map<String, List<Object>> diffMap) {
        StringBuilder formattedDiff = new StringBuilder();
        formattedDiff.append("{\n");
        for (String key: diffMap.keySet()) {
            Object sign = diffMap.get(key).get(1);
            if (!(sign instanceof DiffSigns diffSign)) {
                formattedDiff.append(MINUS.getDiffSign())
                        .append(key)
                        .append(": ")
                        .append(diffMap.get(key).get(0))
                        .append("\n")
                        .append(PLUS.getDiffSign())
                        .append(key)
                        .append(": ")
                        .append(diffMap.get(key).get(1));
            } else {
                formattedDiff.append(diffSign.getDiffSign())
                        .append(key)
                        .append(": ")
                        .append(diffMap.get(key).get(0));
            }
            formattedDiff.append("\n");
        }
        return formattedDiff.append("}").toString();
    }
}
