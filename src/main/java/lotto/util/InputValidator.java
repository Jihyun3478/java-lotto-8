package lotto.util;

import static lotto.common.constant.CommonConstant.DELIMITER;
import static lotto.common.exception.ErrorMessage.INPUT_IS_EMPTY;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_INVALID_FORMAT;

import java.util.Arrays;
import java.util.Objects;

public class InputValidator {
    public static void validateNotBlank(String input) {
        if (Objects.isNull(input) || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_IS_EMPTY.getMessage());
        }
    }

    public static void validateFormat(String input) {
        validateEdgeWithDelimiter(input);
        validateSequenceDelimiter(input);
        validateEachNumberEmpty(input);
    }

    private static void validateEachNumberEmpty(String input) {
        String[] splitInput = input.split(DELIMITER);
        if (Arrays.stream(splitInput).anyMatch(String::isBlank)) {
            throw new IllegalArgumentException(WINNING_NUMBER_INVALID_FORMAT.getMessage());
        }
    }

    private static void validateSequenceDelimiter(String input) {
        if (input.contains(",,")) {
            throw new IllegalArgumentException(WINNING_NUMBER_INVALID_FORMAT.getMessage());
        }
    }

    private static void validateEdgeWithDelimiter(String input) {
        if (input.startsWith(DELIMITER) || input.endsWith(DELIMITER)) {
            throw new IllegalArgumentException(WINNING_NUMBER_INVALID_FORMAT.getMessage());
        }
    }
}
