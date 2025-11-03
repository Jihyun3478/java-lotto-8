package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.MINIMUM_NUMBER_RANGE;
import static lotto.common.exception.ErrorMessage.BONUS_NUMBER_INVALID_RANGE;
import static lotto.common.exception.ErrorMessage.BONUS_NUMBER_MUST_NOT_DUPLICATE;
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

public class BonusNumberTest {
    @Nested
    @DisplayName("보너스 번호 생성 테스트")
    class 보너스_번호_생성_테스트 {
        @Test
        @DisplayName("보너스 번호를 생성한다.")
        void 보너스_번호를_생성한다() {
            WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));
            BonusNumber bonusNumber = new BonusNumber(7, winningNumber);

            assertThat(bonusNumber.getBonusNumber()).isEqualTo(7);
        }
    }

    @Nested
    @DisplayName("보너스 번호 예외 테스트")
    class 보너스_번호_예외_테스트 {
        @ParameterizedTest
        @MethodSource("generateDuplicatesWithWinningNumber")
        @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외가 발생한다.")
        void 보너스_번호가_당첨_번호와_중복될_경우_예외가_발생한다(List<Integer> winningNumbers, int bonusNumber) {
            WinningNumber winningNumber = new WinningNumber(winningNumbers);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new BonusNumber(bonusNumber, winningNumber))
                    .withMessage(BONUS_NUMBER_MUST_NOT_DUPLICATE.getMessage());
        }

        @ParameterizedTest
        @MethodSource("generateInvalidRange")
        @DisplayName("보너스 번호가 1과 45 사이의 숫자가 아닌 경우 예외가 발생한다.")
        void 보너스_번호가_1과_45_사이의_숫자가_아닌_경우_예외가_발생한다(int invalidNumber) {
            WinningNumber winningNumber = new WinningNumber(List.of(1, 2, 3, 4, 5, 6));

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new BonusNumber(invalidNumber, winningNumber))
                    .withMessage(BONUS_NUMBER_INVALID_RANGE.getMessage(MINIMUM_NUMBER_RANGE, MAXIMUM_NUMBER_RANGE));
        }

        private static Stream<Arguments> generateDuplicatesWithWinningNumber() {
            return Stream.of(
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 1),
                    Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6),
                    Arguments.of(List.of(10, 20, 30, 40, 41, 42), 20),
                    Arguments.of(List.of(10, 20, 30, 40, 41, 42), 42)
            );
        }

        private static Stream<Arguments> generateInvalidRange() {
            return Stream.of(
                    Arguments.of(0),
                    Arguments.of(-1),
                    Arguments.of(46),
                    Arguments.of(50),
                    Arguments.of(100),
                    Arguments.of(-100)
            );
        }
    }
}
