package lotto.model.domain;

import static lotto.common.constant.CommonConstant.NUMBERS_SIZE;
import static lotto.common.exception.ErrorMessage.LOTTO_INVALID_SIZE;
import static lotto.common.exception.ErrorMessage.LOTTO_MUST_NOT_DUPLICATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LottoTest {
    @Nested
    @DisplayName("로또 생성 테스트")
    class 로또_생성_테스트 {
        @Test
        @DisplayName("로또를 성공적으로 발행한다.")
        void 로또를_성공적으로_발행한다() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            assertThat(lotto.size()).isEqualTo(6);
        }

        @Test
        @DisplayName("로또 번호는 오름차순으로 정렬한다.")
        void 로또_번호는_오름차순으로_정렬한다() {
            Lotto lotto = new Lotto(List.of(6, 3, 1, 5, 2, 4));

            assertThat(lotto.getLotto()).containsExactly(1, 2, 3, 4, 5, 6);
        }
    }

    @Nested
    @DisplayName("로또 생성 예외 테스트")
    class 로또_생성_예외_테스트 {
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

    @Nested
    @DisplayName("로또 당첨 확인 테스트")
    class 로또_당첨_확인_테스트 {
        @Test
        @DisplayName("당첨 번호와 일치하는 번호 개수를 반환한다.")
        void 당첨_번호와_일치하는_번호_개수를_반환한다() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 7);

            int matchCount = lotto.getMatchCount(winningNumbers);

            assertThat(matchCount).isEqualTo(5);
        }

        @Test
        @DisplayName("보너스 번호가 포함되어 있으면 true를 반환한다.")
        void 보너스_번호가_포함되어_있으면_true를_반환한다() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            boolean matched = lotto.isBonusMatched(6);

            assertThat(matched).isTrue();
        }

        @Test
        @DisplayName("보너스 번호가 포함되어 있지 않으면 false를 반환한다.")
        void 보너스_번호가_포함되어_있지_않으면_false를_반환한다() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            boolean matched = lotto.isBonusMatched(7);

            assertThat(matched).isFalse();
        }

        private List<Integer> parseNumbers(String numbers) {
            return Stream.of(numbers.split(","))
                    .map(Integer::parseInt)
                    .toList();
        }
    }
}
