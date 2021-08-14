package hexlet.code;

import java.util.Map;

public class Formatter {
    public static final String INDENT = "  ";

    public static String format(Map<String,String> mapDiff,
                                Map<String, Object> data1,
                                Map<String, Object> data2,
                                String format) {

        switch (format) {
            case "stylish": return stylishFormatter(mapDiff, data1, data2);
            default: return "Unknown out format";
        }
    }

    public static String stylishFormatter(Map<String,String> mapDiff,
                                          Map<String, Object> data1,
                                          Map<String, Object> data2) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map.Entry<String, String> key : mapDiff.entrySet()) {
            switch (key.getValue()) {
                case "added" -> result.append(INDENT).append("+ ").append(key.getKey()).append(": ")
                        .append(data2.get(key.getKey())).append("\n");
                case "deleted" -> result.append(INDENT).append("- ").append(key.getKey()).append(": ")
                        .append(data1.get(key.getKey())).append("\n");
                case "unchanged" -> result.append(INDENT).append("  ").append(key.getKey()).append(": ")
                        .append(data1.get(key.getKey())).append("\n");
                case "changed" -> result.append(INDENT).append("- ").append(key.getKey()).append(": ")
                        .append(data1.get(key.getKey())).append("\n")
                        .append(INDENT).append("+ ").append(key.getKey()).append(": ")
                        .append(data2.get(key.getKey())).append("\n");
                default -> System.out.println("Unknown format");
            }
        }
        return result.append("}").toString();
    }
}
