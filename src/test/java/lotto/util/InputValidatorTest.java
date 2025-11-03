package lotto.util;

import static lotto.common.exception.ErrorMessage.INPUT_IS_EMPTY;
import static lotto.common.exception.ErrorMessage.INPUT_MUST_NUMBER_FORMAT;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_INVALID_FORMAT;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {
    @Nested
    @DisplayName("입력값 공백 검증 테스트")
    class 입력값_공백_검증_테스트 {
        @Test
        @DisplayName("null이 아니고 비어있지 않으면 검증을 통과한다.")
        void null이_아니고_비어있지_않으면_검증을_통과한다() {
            assertThatCode(() -> InputValidator.validateNotBlank("1000"))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "     ", "\t", "\n", "   \t   "})
        @DisplayName("입력값이 null이거나 비어있는 경우 예외가 발생한다.")
        void 입력값이_null이거나_비어있는_경우_예외가_발생한다(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputValidator.validateNotBlank(input))
                    .withMessage(INPUT_IS_EMPTY.getMessage());
        }
    }

    @Nested
    @DisplayName("당첨 번호 형식 검증 테스트")
    class 당첨_번호_형식_검증_테스트 {
        @ParameterizedTest
        @ValueSource(strings = {"1,2,3,4,5,6", "10,20,30,40,41,42", "1,2,3,4,5,45"})
        @DisplayName("올바른 형식의 당첨 번호는 검증을 통과한다.")
        void 올바른_형식의_당첨_번호는_검증을_통과한다(String input) {
            assertThatCode(() -> InputValidator.validateFormat(input))
                    .doesNotThrowAnyException();
        }

        @ParameterizedTest
        @MethodSource("generateInvalidEdgeFormats")
        @DisplayName("양 끝에 구분자가 있으면 예외가 발생한다.")
        void 양_끝에_구분자가_있으면_예외가_발생한다(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputValidator.validateFormat(input))
                    .withMessage(WINNING_NUMBER_INVALID_FORMAT.getMessage());
        }

        @ParameterizedTest
        @MethodSource("generateSequenceDelimiterFormats")
        @DisplayName("연속된 구분자가 있으면 예외가 발생한다.")
        void 연속된_구분자가_있으면_예외가_발생한다(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputValidator.validateFormat(input))
                    .withMessage(WINNING_NUMBER_INVALID_FORMAT.getMessage());
        }

        @ParameterizedTest
        @MethodSource("generateEmptySpaceFormats")
        @DisplayName("빈 문자열이 포함되면 예외가 발생한다.")
        void 빈_문자열이_포함되면_예외가_발생한다(String input) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> InputValidator.validateFormat(input))
                    .withMessage(WINNING_NUMBER_INVALID_FORMAT.getMessage());
        }

        private static Stream<Arguments> generateInvalidEdgeFormats() {
            return Stream.of(
                    Arguments.of(",1,2,3,4,5,6"),
                    Arguments.of("1,2,3,4,5,6,"),
                    Arguments.of(",1,2,3,4,5,6,")
            );
        }

        private static Stream<Arguments> generateSequenceDelimiterFormats() {
            return Stream.of(
                    Arguments.of("1,,2,3,4,5,6"),
                    Arguments.of("1,2,,3,4,5,6"),
                    Arguments.of("1,2,3,4,,5,6"),
                    Arguments.of("1,2,3,,,4,5,6")
            );
        }

        private static Stream<Arguments> generateEmptySpaceFormats() {
            return Stream.of(
                    Arguments.of("1, ,3,4,5,6"),
                    Arguments.of("1,2,  ,4,5,6"),
                    Arguments.of("1,2,3, ,5,6"),
                    Arguments.of(" ,2,3,4,5,6")
            );
        }
    }
}
