package hexlet.code.formatters;

import hexlet.code.DifferenceInfo;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Stylish {
    public static final String INDENT = "  ";

    public static String format(Set<DifferenceInfo> differenceInfoSet) {
        TreeSet<DifferenceInfo> differenceInfoTreeSet = new TreeSet<>(Comparator.comparing(DifferenceInfo::getKey));
        differenceInfoTreeSet.addAll(differenceInfoSet);
        StringBuilder result = new StringBuilder("{\n");
        for (DifferenceInfo item : differenceInfoTreeSet) {
            switch (item.getChangeStatus()) {
                case ADDED -> result.append(INDENT).append("+ ").append(item.getKey()).append(": ")
                        .append(item.getNewValue()).append("\n");
                case DELETED -> result.append(INDENT).append("- ").append(item.getKey()).append(": ")
                        .append(item.getOldValue()).append("\n");
                case UNCHANGED -> result.append(INDENT).append("  ").append(item.getKey()).append(": ")
                        .append(item.getNewValue()).append("\n");
                case CHANGED -> result.append(INDENT).append("- ").append(item.getKey()).append(": ")
                        .append(item.getOldValue()).append("\n")
                        .append(INDENT).append("+ ").append(item.getKey()).append(": ")
                        .append(item.getNewValue()).append("\n");
                default -> { // объекты в прочих статусах не имеют представления при выводе на печать
                }
            }
        }
        return result.append("}").toString();
    }
}
