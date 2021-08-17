package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.DifferenceInfo;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Json {
    public static final String INDENT = "  ";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Comparator<DifferenceInfo> KEY_ORDER = Comparator.comparing(DifferenceInfo::getKey);

    public static String format(Set<DifferenceInfo> differenceInfoSet) {
        Set<DifferenceInfo> differenceInfoTreeSet = new TreeSet<>(KEY_ORDER);
        differenceInfoTreeSet.addAll(differenceInfoSet);
        StringBuilder result = new StringBuilder("{\n").append(INDENT).append("\"diffs\": [\n");
        for (DifferenceInfo item : differenceInfoTreeSet) {
            try {
                result.append(INDENT.repeat(2))
                        .append(MAPPER.writeValueAsString(item))
                        .append(",\n");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return result.append(INDENT).append("]\n}\n").toString();
    }
}
