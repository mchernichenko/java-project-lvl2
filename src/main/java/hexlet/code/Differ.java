package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Map<String, Object> dataFile1 = Parser.parser(filePath1);
        Map<String, Object> dataFile2 = Parser.parser(filePath2);
        Map<String, String> mapDiff = genDiff(dataFile1, dataFile2);
        return Formatter.format(mapDiff, dataFile1, dataFile2, format);
    }

    private static Map<String,String> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
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
            if (Objects.equals(data1.get(key), data2.get(key))) {
                diffMap.put(key, "unchanged");
            } else {
                diffMap.put(key, "changed");
            }
        }
        return diffMap;
    }
}
