package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Set;

public class Formatter {

    public static String format(Set<DifferenceInfo> differenceInfoSet,
                                String format) {
        return switch (format) {
            case "stylish" -> Stylish.format(differenceInfoSet);
            case "plain" -> Plain.format(differenceInfoSet);
            default -> "Unknown out format";
        };
    }
}
