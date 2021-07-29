package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Differ {
    public static final String INDENT = "  ";
    public static String generate(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> intersectKeys = data1.keySet().stream()
                .filter(data2::containsKey)
                .collect(Collectors.toSet());

        Map<String, String> addMap = data2.keySet().stream()
                .filter(x -> !intersectKeys.contains(x))
                .collect(Collectors.toMap(x -> x, y -> "added"));

        Map<String, String> delMap = data1.keySet().stream()
                .filter(x -> !intersectKeys.contains(x))
                .collect(Collectors.toMap(x -> x, y -> "deleted"));

        Map<String, String> diffMap = new TreeMap<>(addMap);
        diffMap.putAll(delMap);

        for (String key : intersectKeys) {
            if (data1.get(key).equals(data2.get(key))) {
                diffMap.put(key, "unchanged");
            } else {
                diffMap.put(key, "changed");
            }
        }

        StringBuilder result = new StringBuilder("{\n");
        for (Map.Entry<String, String> key : diffMap.entrySet()) {
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
            }
        }
        return result.append("}").toString();
    }
}
