package lotto;

import java.util.Objects;

public class InputValidator {
    public static int parsePurchaseAmount(String input) {
        validateNotBlank(input);
        return parseToNumber(input);
    }

    private static void validateNotBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
        }
    }

    private static int parseToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }
}
