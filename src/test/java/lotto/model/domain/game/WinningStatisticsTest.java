package lotto.model.domain.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import lotto.model.domain.PurchaseAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WinningStatisticsTest {
    @Nested
    @DisplayName("당첨 통계 기록 테스트")
    class 당첨_통계_기록_테스트 {
        @Test
        @DisplayName("등수별 당첨 개수를 기록한다.")
        void 등수별_당첨_개수를_기록한다() {
            WinningStatistics statistics = new WinningStatistics();

            statistics.add(Rank.FIFTH);
            statistics.add(Rank.FIFTH);
            statistics.add(Rank.FOURTH);

            assertThat(statistics.getCountByRank(Rank.FIFTH)).isEqualTo(2);
            assertThat(statistics.getCountByRank(Rank.FOURTH)).isEqualTo(1);
        }

        @Test
        @DisplayName("기록되지 않은 등수의 개수는 0이다.")
        void 기록되지_않은_등수의_개수는_0이다() {
            WinningStatistics statistics = new WinningStatistics();

            assertThat(statistics.getCountByRank(Rank.FIRST)).isZero();
        }

        @Test
        @DisplayName("null 등수를 추가해도 예외가 발생하지 않는다.")
        void null_등수를_추가해도_예외가_발생하지_않는다() {
            WinningStatistics statistics = new WinningStatistics();

            assertThatCode(() -> statistics.add(null))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @DisplayName("수익률 계산 테스트")
    class 수익률_계산_테스트 {
        @Test
        @DisplayName("당첨 금액이 없으면 수익률은 0이다.")
        void 당첨_금액이_없으면_수익률은_0이다() {
            WinningStatistics statistics = new WinningStatistics();
            PurchaseAmount amount = new PurchaseAmount(8000);

            double profitRate = statistics.calculatePrizePercent(amount);

            assertThat(profitRate).isZero();
        }

        @ParameterizedTest
        @CsvSource({
                "5000, 100.0",
                "8000, 62.5",
                "10000, 50.0",
                "6000, 83.33"
        })
        @DisplayName("5등 1개 당첨 시 수익률을 계산한다.")
        void 오등_1개_당첨_시_수익률을_계산한다(int purchaseAmount, double expectedRate) {
            WinningStatistics statistics = new WinningStatistics();
            statistics.add(Rank.FIFTH);

            PurchaseAmount amount = new PurchaseAmount(purchaseAmount);
            double profitRate = statistics.calculatePrizePercent(amount);

            assertThat(profitRate).isEqualTo(expectedRate);
        }

        @Test
        @DisplayName("여러 등수의 당첨 금액을 합산하여 수익률을 계산한다.")
        void 여러_등수의_당첨_금액을_합산하여_수익률을_계산한다() {
            WinningStatistics statistics = new WinningStatistics();
            statistics.add(Rank.FOURTH);
            statistics.add(Rank.THIRD);

            PurchaseAmount amount = new PurchaseAmount(5000);
            double profitRate = statistics.calculatePrizePercent(amount);

            assertThat(profitRate).isEqualTo(31000.0);
        }

        @Test
        @DisplayName("수익률은 소수점 둘째 자리에서 반올림힌다.")
        void 수익률은_소수점_둘째_자리에서_반올림한다() {
            WinningStatistics statistics = new WinningStatistics();
            statistics.add(Rank.FIFTH);

            PurchaseAmount amount = new PurchaseAmount(6000);
            double profitRate = statistics.calculatePrizePercent(amount);

            assertThat(profitRate).isEqualTo(83.33);
        }
    }
}
