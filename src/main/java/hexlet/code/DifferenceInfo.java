package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Внутреннее представление диф`а между исходными структурами. Описывает каждый ключ.
 */
@Data
@AllArgsConstructor
public class DifferenceInfo {
    private String key;      // unique
    private Object oldValue;
    private Object newValue;
    private ChangeStatus changeStatus;

    public enum ChangeStatus {
        ADDED, DELETED, CHANGED, UNCHANGED
    }
}



