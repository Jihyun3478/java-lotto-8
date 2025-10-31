package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusNumberTest {
    @Test
    @DisplayName("당첨 번호가 6개가 아닌 경우 예외가 발생한다.")
    void 당첨_번호가_6개가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new BonusNumber())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 1과 45 사이의 숫자가 아닌 경우 예외가 발생한다.")
    void 당첨_번호가_1과_45사이의_숫자가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new BonusNumber())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 1과 45 사이의 숫자여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 중복된 경우 예외가 발생한다.")
    void 당첨_번호가_중복된_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new BonusNumber())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 중복되지 않아야 합니다.");
    }
}
