package lotto.util;

import static lotto.common.exception.ErrorMessage.INPUT_MUST_NUMBER_FORMAT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class InputParserTest {
    @Nested
    @DisplayName("숫자 변환 테스트")
    class 숫자_변환_테스트 {
        @ParameterizedTest
        @CsvSource({
                "1000, 1000",
                "5000, 5000",
                "10000, 10000",
                "100000, 100000"
        })
        @DisplayName("숫자 문자열을 정수로 변환한다.")
        void 숫자_문자열을_정수로_변환한다(String input, int expected) {
            int number = InputParser.parseNumber(input);

            assertThat(number).isEqualTo(expected);
        }

        @ParameterizedTest
        @MethodSource("generateInvalidNumberFormats")
        @DisplayName("숫자가 아닌 문자열은 예외가 발생한다.")
        void 숫자가_아닌_문자열은_예외가_발생한다(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputParser.parseNumber(input))
                    .withMessage(INPUT_MUST_NUMBER_FORMAT.getMessage());
        }

        private static Stream<Arguments> generateInvalidNumberFormats() {
            return Stream.of(
                    Arguments.of("입력"),
                    Arguments.of("금액"),
                    Arguments.of("abc"),
                    Arguments.of("1a2b"),
                    Arguments.of("12.34"),
                    Arguments.of("1,000")
            );
        }
    }

    @Nested
    @DisplayName("당첨 번호 변환 테스트")
    class 당첨_번호_변환_테스트 {
        @Test
        @DisplayName("당첨 번호 문자열을 리스트로 변환한다.")
        void 당첨_번호_문자열을_리스트로_변환한다() {
            String input = "1,2,3,4,5,6";

            List<Integer> numbers = InputParser.parseWinningNumbers(input);

            assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
        }

        @ParameterizedTest
        @MethodSource("generateWinningNumbersWithSpace")
        @DisplayName("당첨 번호에 공백이 포함되어도 정상 처리한다.")
        void 당첨_번호에_공백이_포함되어도_정상_처리한다(String input, List<Integer> expected) {
            List<Integer> numbers = InputParser.parseWinningNumbers(input);

            assertThat(numbers).containsExactlyElementsOf(expected);
        }

        @ParameterizedTest
        @MethodSource("generateInvalidWinningNumberFormats")
        @DisplayName("잘못된 형식의 당첨 번호는 예외가 발생한다.")
        void 잘못된_형식의_당첨_번호는_예외가_발생한다(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputParser.parseWinningNumbers(input));
        }

        @ParameterizedTest
        @MethodSource("generateNotNumbers")
        @DisplayName("당첨 번호에 숫자가 아닌 값이 포함되면 예외가 발생한다.")
        void 당첨_번호에_숫자가_아닌_값이_포함되면_예외가_발생한다(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputParser.parseWinningNumbers(input))
                    .withMessage(INPUT_MUST_NUMBER_FORMAT.getMessage());
        }

        private static Stream<Arguments> generateWinningNumbersWithSpace() {
            return Stream.of(
                    Arguments.of("1, 2, 3, 4, 5, 6", List.of(1, 2, 3, 4, 5, 6)),
                    Arguments.of("1,  2,  3,  4,  5,  6", List.of(1, 2, 3, 4, 5, 6)),
                    Arguments.of(" 1, 2, 3, 4, 5, 6 ", List.of(1, 2, 3, 4, 5, 6))
            );
        }

        private static Stream<Arguments> generateInvalidWinningNumberFormats() {
            return Stream.of(
                    Arguments.of("1,2,3,,4,5,6"),
                    Arguments.of(",1,2,3,4,5,6"),
                    Arguments.of("1,2,3,4,5,6,")
            );
        }

        private static Stream<Arguments> generateNotNumbers() {
            return Stream.of(
                    Arguments.of("a,b,c,d,e,f"),
                    Arguments.of("1,2,3,four,5,6"),
                    Arguments.of("1,2,3,4,5,육"),
                    Arguments.of("1.5,2,3,4,5,6")
            );
        }
    }
}
