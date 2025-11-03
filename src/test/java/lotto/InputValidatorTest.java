package lotto;

import static lotto.common.exception.ErrorMessage.INPUT_IS_EMPTY;
import static lotto.common.exception.ErrorMessage.INPUT_MUST_NUMBER_FORMAT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.common.util.InputParser;
import lotto.common.util.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     "})
    @DisplayName("입력값이 비어있는 경우 예외가 발생한다.")
    void 입력값이_비어있는_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputValidator.validateNotBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INPUT_IS_EMPTY.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"입력", "금액"})
    @DisplayName("입력값이 숫자가 아닌 경우 예외가 발생한다.")
    void 입려값이_숫자가_아닌_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputParser.parseNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_MUST_NUMBER_FORMAT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,,4,5,6", "1.2.3.4.5.6", ",1,2,3,4,5,6", "1,2,3,4,5,6,,,"})
    @DisplayName("입력값이 잘못된 형식일 경우 예외가 발생한다.")
    void 입력값이_잘못된_형식일_경우_예외가_발생한다(String input) {
        assertThatThrownBy(() -> InputParser.parseNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INPUT_MUST_NUMBER_FORMAT.getMessage());
    }
}
