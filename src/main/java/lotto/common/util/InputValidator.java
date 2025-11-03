package lotto.common.util;

import java.util.Objects;

public class InputValidator {
    public static void validateNotBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }
}
