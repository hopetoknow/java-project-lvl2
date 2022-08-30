package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {

    public static String format(Map<String, List<Object>> diffMap, String formatName) {
        if (formatName.equals("plain")) {
            return PlainFormatter.format(diffMap);
        } else {
            return StylishFormatter.format(diffMap);
        }
    }
}
