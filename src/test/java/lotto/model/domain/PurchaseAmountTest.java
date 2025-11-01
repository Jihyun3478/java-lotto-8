package lotto.model.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PurchaseAmountTest {
    @ParameterizedTest
    @ValueSource(ints = {1100, 1500, 2800})
    @DisplayName("로또 구입 금액이 1000원 단위가 아닐 경우 예외가 발생한다.")
    void 구입_금액이_1000원_단위가_아닐_경우_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new PurchaseAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1000, 0})
    @DisplayName("로또 구입 금액이 0보다 작거나 같을 경우 예외가 발생한다.")
    void 구입_금액이_0보다_작거나_같을_경우_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new PurchaseAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1000원 이상이여야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {150000, 1000000, 50000000})
    @DisplayName("로또 구입 금액이 10만원을 초과할 경우 예외가 발생한다.")
    void 구입_금액이_10만원을_초과할_경우_예외가_발생한다(int money) {
        assertThatThrownBy(() -> new PurchaseAmount(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 100,000원을 초과할 수 없습니다.");
    }
}
