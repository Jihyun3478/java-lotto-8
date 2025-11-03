package lotto.model.domain;

import static lotto.common.constant.CommonConstant.NUMBERS_SIZE;
import static lotto.common.exception.ErrorMessage.LOTTO_INVALID_SIZE;
import static lotto.common.exception.ErrorMessage.LOTTO_MUST_NOT_DUPLICATE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.model.domain.game.NumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Test
    @DisplayName("로또를 성공적으로 발행한다.")
    void 로또를_발행한다() {
        NumberGenerator randomNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = new Lotto(randomNumberGenerator.generate());

        assertEquals(6, lotto.size());
    }

    @Test
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_INVALID_SIZE.getMessage(NUMBERS_SIZE));
    }

    @Test
    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(LOTTO_MUST_NOT_DUPLICATE.getMessage());
    }
}
