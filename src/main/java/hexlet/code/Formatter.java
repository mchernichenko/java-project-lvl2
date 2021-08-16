package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Set;

public class Formatter {

    public static String format(Set<DifferenceInfo> differenceInfoSet,
                                Formatter.Format format) {
        return switch (format) {
            case STYLISH -> Stylish.format(differenceInfoSet);
            case PLAIN -> Plain.format(differenceInfoSet);
            case JSON -> Json.format(differenceInfoSet);
        };
    }

    public enum Format {
        STYLISH("stylish"),
        PLAIN("plain"),
        JSON("json");

        private final String abbreviation;

        Format(String abbreviation) {
            this.abbreviation = abbreviation;
        }

        public String getAbbreviation() {
            return abbreviation;
        }
    }
}
