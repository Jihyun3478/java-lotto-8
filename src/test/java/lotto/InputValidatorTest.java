package lotto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.util.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     "})
    @DisplayName("입력값이 비어있는 경우 예외가 발생한다.")
    void 입력값이_비어있는_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.parsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 입력값이 비어있습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"입력", "금액"})
    @DisplayName("로또 구입 금액이 숫자가 아닌 경우 예외가 발생한다.")
    void 구입_금액이_숫자가_아닌_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.parsePurchaseAmount(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 숫자여야 합니다.");
    }
}
