package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_PURCHASE_AMOUNT;
import static lotto.common.constant.CommonConstant.PURCHASE_UNIT;
import static lotto.common.exception.ErrorMessage.PURCHASE_AMOUNT_INVALID_UNIT;
import static lotto.common.exception.ErrorMessage.PURCHASE_AMOUNT_LESS_THAN_TEN_THOUSAND;
import static lotto.common.exception.ErrorMessage.PURCHASE_AMOUNT_MORE_THAN_THOUSAND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class PurchaseAmountTest {
    @Nested
    @DisplayName("구입 금액 생성 테스트")
    class 구입_금액_생성_테스트 {
        @Test
        @DisplayName("유효한 구입 금액으로 객체를 생성한다.")
        void 유효한_구입_금액으로_객체를_생성한다() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(8000);

            assertThat(purchaseAmount.getPurchaseAmount()).isEqualTo(8000);
        }

        @ParameterizedTest
        @CsvSource({
                "1000, 1",
                "5000, 5",
                "8000, 8",
                "10000, 10",
                "100000, 100"
        })
        @DisplayName("구입 금액에 따른 로또 발행 개수를 반환한다.")
        void 구입_금액에_따른_로또_발행_개수를_반환한다(int amount, int expectedCount) {
            PurchaseAmount purchaseAmount = new PurchaseAmount(amount);

            assertThat(purchaseAmount.countPublishLotto()).isEqualTo(expectedCount);
        }

        @Test
        @DisplayName("최소 금액 1000원으로 로또를 구입할 수 있다.")
        void 최소_금액_1000원으로_로또를_구입할_수_있다() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);

            assertThat(purchaseAmount.countPublishLotto()).isEqualTo(1);
        }

        @Test
        @DisplayName("최대 금액 100000원으로 로또를 구입할 수 있다.")
        void 최대_금액_100000원으로_로또를_구입할_수_있다() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(100000);

            assertThat(purchaseAmount.countPublishLotto()).isEqualTo(100);
        }
    }

    @Nested
    @DisplayName("구입 금액 예외 테스트")
    class 구입_금액_예외_테스트 {
        @ParameterizedTest
        @MethodSource("generateInvalidUnit")
        @DisplayName("로또 구입 금액이 1000원 단위가 아닐 경우 예외가 발생한다.")
        void 구입_금액이_1000원_단위가_아닐_경우_예외가_발생한다(int purchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchaseAmount(purchaseAmount))
                    .withMessage(PURCHASE_AMOUNT_INVALID_UNIT.getMessage(PURCHASE_UNIT));
        }

        @ParameterizedTest
        @MethodSource("generateLowerThanMinimumAmount")
        @DisplayName("로또 구입 금액이 최소 금액보다 작을 경우 예외가 발생한다.")
        void 구입_금액이_최소_금액보다_작을_경우_예외가_발생한다(int purchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchaseAmount(purchaseAmount))
                    .withMessage(PURCHASE_AMOUNT_MORE_THAN_THOUSAND.getMessage(PURCHASE_UNIT));
        }

        @ParameterizedTest
        @MethodSource("generateGreaterThanMaximumAmount")
        @DisplayName("로또 구입 금액이 최대 금액을 초과할 경우 예외가 발생한다.")
        void 구입_금액이_최대_금액을_초과할_경우_예외가_발생한다(int purchaseAmount) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new PurchaseAmount(purchaseAmount))
                    .withMessage(PURCHASE_AMOUNT_LESS_THAN_TEN_THOUSAND.getMessage(MAXIMUM_PURCHASE_AMOUNT));
        }

        private static Stream<Arguments> generateInvalidUnit() {
            return Stream.of(
                    Arguments.of(999),
                    Arguments.of(1100),
                    Arguments.of(1500),
                    Arguments.of(2800),
                    Arguments.of(5555)
            );
        }

        private static Stream<Arguments> generateLowerThanMinimumAmount() {
            return Stream.of(
                    Arguments.of(-1000),
                    Arguments.of(-2000),
                    Arguments.of(0),
                    Arguments.of(-3000)
            );
        }

        private static Stream<Arguments> generateGreaterThanMaximumAmount() {
            return Stream.of(
                    Arguments.of(101000),
                    Arguments.of(150000),
                    Arguments.of(1000000),
                    Arguments.of(50000000)
            );
        }
    }
}
