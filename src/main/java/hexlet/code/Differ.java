package hexlet.code;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object> dataFile1 = Parser.parser(filePath1);
        Map<String, Object> dataFile2 = Parser.parser(filePath2);
        Set<DifferenceInfo> differenceInfoSet = genDiff(dataFile1, dataFile2);
        return Formatter.format(differenceInfoSet, Enum.valueOf(Formatter.Format.class, formatName));
    }

    private static Set<DifferenceInfo> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Set<String> intersectKeys = data1.keySet().stream()
                .filter(data2::containsKey)
                .collect(Collectors.toSet());

        Set<DifferenceInfo> addSet = data2.keySet().stream()
                .filter(x -> !intersectKeys.contains(x))
                .map(x -> new DifferenceInfo(x, null, data2.get(x), DifferenceInfo.ChangeStatus.ADDED))
                .collect(Collectors.toSet());

        Set<DifferenceInfo> delSet = data1.keySet().stream()
                .filter(x -> !intersectKeys.contains(x))
                .map(x -> new DifferenceInfo(x, data1.get(x), null, DifferenceInfo.ChangeStatus.DELETED))
                .collect(Collectors.toSet());

        Set<DifferenceInfo> differenceInfoSet = new HashSet<>(addSet);
        differenceInfoSet.addAll(delSet);

        for (String key : intersectKeys) {
            if (Objects.equals(data1.get(key), data2.get(key))) {
                differenceInfoSet.add(new DifferenceInfo(
                        key,
                        data2.get(key),
                        data2.get(key),
                        DifferenceInfo.ChangeStatus.UNCHANGED));
            } else {
                differenceInfoSet.add(new DifferenceInfo(
                        key,
                        data1.get(key),
                        data2.get(key),
                        DifferenceInfo.ChangeStatus.CHANGED));
            }
        }
        return differenceInfoSet;
    }
}
