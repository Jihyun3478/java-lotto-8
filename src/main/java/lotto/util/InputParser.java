package lotto.util;

import static lotto.common.constant.CommonConstant.DELIMITER;
import static lotto.common.exception.ErrorMessage.INPUT_MUST_NUMBER_FORMAT;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_MUST_NUMBER_FORMAT.getMessage());
        }
    }

    public static List<Integer> parseWinningNumbers(String input) {
        InputValidator.validateFormat(input);

        try {
            String[] splitInput = input.split(DELIMITER, -1);

            return Arrays.stream(splitInput)
                    .map(String::strip)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INPUT_MUST_NUMBER_FORMAT.getMessage());
        }
    }
}
