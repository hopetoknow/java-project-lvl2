package hexlet.code.formatters;

import hexlet.code.DiffSigns;

import java.util.List;
import java.util.Map;

import static hexlet.code.DiffSigns.MINUS;
import static hexlet.code.DiffSigns.PLUS;
import static hexlet.code.DiffSigns.SPACE;

public class PlainFormatter {

    public static String format(Map<String, List<Object>> diffMap) {
        StringBuilder formattedDiff = new StringBuilder();
        for (String key: diffMap.keySet()) {
            Object firstListValue = diffMap.get(key).get(0);
            Object secondListValue = diffMap.get(key).get(1);
            if (!(secondListValue instanceof DiffSigns diffSign)) {
                appendKeyAndSurroundingText(key, formattedDiff);
                formattedDiff.append("updated. From ");
                appendValue(firstListValue, formattedDiff);
                formattedDiff.append(" to ");
                appendValue(secondListValue, formattedDiff);
            } else if (diffSign.equals(SPACE)) {
                continue;
            } else if (diffSign.equals(MINUS)) {
                appendKeyAndSurroundingText(key, formattedDiff);
                formattedDiff.append("removed");
            } else if (diffSign.equals(PLUS)) {
                appendKeyAndSurroundingText(key, formattedDiff);
                formattedDiff.append("added with value: ");
                appendValue(firstListValue, formattedDiff);
            }
            formattedDiff.append("\n");
        }
        return formattedDiff.length() > 0 ? formattedDiff.substring(0, formattedDiff.length() - 1)
                : formattedDiff.toString();
    }

    private static boolean isPrimitive(Object obj) {
        if (obj == null) {
            return true;
        }
        Class<?> clazz = obj.getClass();
        return clazz == Boolean.class || clazz == Character.class || clazz == Byte.class || clazz == Short.class
                || clazz == Integer.class || clazz == Long.class || clazz == Float.class || clazz == Double.class
                || clazz == String.class;
    }

    private static void appendValue(Object value, StringBuilder formattedDiff) {
        if (isPrimitive(value)) {
            if (value instanceof String) {
                formattedDiff.append("'")
                        .append(value)
                        .append("'");
            } else {
                formattedDiff.append(value);
            }
        } else {
            formattedDiff.append("[complex value]");
        }
    }

    private static void appendKeyAndSurroundingText(String key, StringBuilder formattedDiff) {
        formattedDiff.append("Property '")
                .append(key)
                .append("' was ");
    }
}
