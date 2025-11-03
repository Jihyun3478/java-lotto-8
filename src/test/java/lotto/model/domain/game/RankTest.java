package lotto.model.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {
    @Nested
    @DisplayName("등수 판단 테스트")
    class 등수_판단_테스트 {
        @ParameterizedTest
        @CsvSource({
                "3, false, FIFTH",
                "4, false, FOURTH",
                "5, false, THIRD",
                "5, true, SECOND",
                "6, false, FIRST",
                "6, true, FIRST"
        })
        @DisplayName("일치 개수와 보너스 매치 여부로 등수를 판단한다.")
        void 일치_개수와_보너스_매치_여부로_등수를_판단한다(int matchCount, boolean bonusMatched, Rank expected) {
            Rank rank = Rank.of(matchCount, bonusMatched);

            assertThat(rank).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource({
                "0, false",
                "1, false",
                "2, false",
                "0, true",
                "1, true",
                "2, true",
        })
        @DisplayName("당첨되지 않은 경우 NONE을 반환한다.")
        void 당첨되지_않은_경우_NONE을_반환한다(int matchCount, boolean bonusMatched) {
            Rank rank = Rank.of(matchCount, bonusMatched);

            assertThat(rank).isEqualTo(Rank.NONE);
        }
    }
}
