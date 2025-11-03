package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.MINIMUM_NUMBER_RANGE;
import static lotto.common.exception.ErrorMessage.BONUS_NUMBER_INVALID_RANGE;
import static lotto.common.exception.ErrorMessage.BONUS_NUMBER_MUST_NOT_DUPLICATE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusNumberTest {
    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복될 경우 예외가 발생한다.")
    void 보너스_번호가_당첨_번호와_중복될_경우_예외가_발생한다() {
        WinningNumber winningNumber = new WinningNumber(List.of(1,2,3,4,5,6));

        assertThatThrownBy(() -> new BonusNumber(1, winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_MUST_NOT_DUPLICATE.getMessage());
    }

    @Test
    @DisplayName("보너스 번호가 1과 45 사이의 숫자가 아닌 경우 예외가 발생한다.")
    void 보너스_번호가_1과_45_사이의_숫자가_아닌_경우_예외가_발생한다() {
        WinningNumber winningNumber = new WinningNumber(List.of(1,2,3,4,5,6));

        assertThatThrownBy(() -> new BonusNumber(0, winningNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_NUMBER_INVALID_RANGE.getMessage(MINIMUM_NUMBER_RANGE, MAXIMUM_NUMBER_RANGE));
    }
}
