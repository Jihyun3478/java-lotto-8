package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.MINIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.NUMBERS_SIZE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_DUPLICATE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_INVALID_RANGE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_INVALID_SIZE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumberTest {
    @Test
    @DisplayName("당첨 번호가 6개가 아닌 경우 예외가 발생한다.")
    void 당첨_번호가_6개가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumber(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBER_INVALID_SIZE.getMessage(NUMBERS_SIZE));
    }

    @Test
    @DisplayName("당첨 번호가 1과 45 사이의 숫자가 아닌 경우 예외가 발생한다.")
    void 당첨_번호가_1과_45사이의_숫자가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumber(List.of(0, 1, 2, 3, 4, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBER_INVALID_RANGE.getMessage(MINIMUM_NUMBER_RANGE, MAXIMUM_NUMBER_RANGE));
    }

    @Test
    @DisplayName("당첨 번호가 중복된 경우 예외가 발생한다.")
    void 당첨_번호가_중복된_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningNumber(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(WINNING_NUMBER_DUPLICATE.getMessage());
    }
}
