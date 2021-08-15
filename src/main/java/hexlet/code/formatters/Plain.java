package hexlet.code.formatters;

import hexlet.code.DifferenceInfo;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Plain {

    public static String format(Set<DifferenceInfo> differenceInfoSet) {
        TreeSet<DifferenceInfo> differenceInfoTreeSet = new TreeSet<>(Comparator.comparing(DifferenceInfo::getKey));
        differenceInfoTreeSet.addAll(differenceInfoSet);
        StringBuilder result = new StringBuilder();
        for (DifferenceInfo item : differenceInfoTreeSet) {
            switch (item.getChangeStatus()) {
                case ADDED -> result.append(String.format("Property '%s' was added with value: %s",
                                item.getKey(),
                                objectToString(item.getNewValue())))
                        .append("\n");
                case DELETED -> result.append(String.format("Property '%s' was removed", item.getKey()))
                        .append("\n");
                case CHANGED -> result.append(String.format("Property '%s' was updated. From %s to %s",
                                item.getKey(),
                                objectToString(item.getOldValue()),
                                objectToString(item.getNewValue())))
                        .append("\n");
                default -> {
                }
            }
        }
        return result.toString().trim();
    }

    /**
     * Замена составного объекта.
     * @param object
     * @return Если object является составным (объект или массив), то заменяем его на [complex value]
     */
    private static Object objectToString(Object object) {
        return object instanceof Object[] || object instanceof Collection || object instanceof Map
                ? "[complex value]"
                : object instanceof String
                ? "'" + object + "'"
                : object;
    }
}
