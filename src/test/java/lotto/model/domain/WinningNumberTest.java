package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.MINIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.NUMBERS_SIZE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_DUPLICATE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_INVALID_RANGE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_INVALID_SIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningNumberTest {
    @Nested
    @DisplayName("당첨 번호 생성 테스트")
    class 당첨_번호_생성_테스트 {
        @Test
        @DisplayName("당첨 번호를 생성한다.")
        void 당첨_번호를_생성한다() {
            WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));

            assertThat(winningNumber.getWinningNumber()).hasSize(6);
        }

        @Test
        @DisplayName("보너스 번호 포함 여부를 확인할 수 있다.")
        void 보너스_번호_포함_여부를_확인할_수_있다() {
            WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));

            assertThat(winningNumber.isContainBonusNumber(6)).isTrue();
            assertThat(winningNumber.isContainBonusNumber(7)).isFalse();
        }
    }

    @Nested
    @DisplayName("당첨 번호 예외 테스트")
    class 당첨_번호_예외_테스트 {
        @ParameterizedTest
        @MethodSource("generateInvalidSize")
        @DisplayName("당첨 번호가 6개가 아닌 경우 예외가 발생한다.")
        void 당첨_번호가_6개가_아닌_경우_예외가_발생한다(List<Integer> numbers) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new WinningNumber(numbers))
                    .withMessage(WINNING_NUMBER_INVALID_SIZE.getMessage(NUMBERS_SIZE));
        }

        @ParameterizedTest
        @MethodSource("generateInvalidRange")
        @DisplayName("당첨 번호가 1과 45 사이의 숫자가 아닌 경우 예외가 발생한다.")
        void 당첨_번호가_1과_45사이의_숫자가_아닌_경우_예외가_발생한다(List<Integer> numbers) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new WinningNumber(numbers))
                    .withMessage(WINNING_NUMBER_INVALID_RANGE.getMessage(MINIMUM_NUMBER_RANGE, MAXIMUM_NUMBER_RANGE));
        }

        @ParameterizedTest
        @MethodSource("generateDuplicates")
        @DisplayName("당첨 번호가 중복된 경우 예외가 발생한다.")
        void 당첨_번호가_중복된_경우_예외가_발생한다(List<Integer> numbers) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new WinningNumber(numbers))
                    .withMessage(WINNING_NUMBER_DUPLICATE.getMessage());
        }

        private static Stream<Arguments> generateInvalidSize() {
            return Stream.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
                    Arguments.of(List.of(1)),
                    Arguments.of(List.of())
            );
        }

        private static Stream<Arguments> generateInvalidRange() {
            return Stream.of(
                    Arguments.of(List.of(0, 1, 2, 3, 4, 5)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 46)),
                    Arguments.of(List.of(-1, 1, 2, 3, 4, 5)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 100))
            );
        }

        private static Stream<Arguments> generateDuplicates() {
            return Stream.of(
                    Arguments.of(List.of(1, 1, 2, 3, 4, 5)),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 5)),
                    Arguments.of(List.of(1, 1, 1, 1, 1, 1)),
                    Arguments.of(List.of(10, 20, 30, 10, 40, 45))
            );
        }
    }
}
