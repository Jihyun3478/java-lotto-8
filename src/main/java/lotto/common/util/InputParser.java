package lotto.common.util;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static int parseToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    public static List<Integer> parseToWinningNumber(String input) {
        String[] splitInput = input.split(",", -1);

        return Arrays.stream(splitInput)
                .map(String::strip)
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }
}
