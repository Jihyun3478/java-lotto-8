package lotto.model.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.model.domain.game.NumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
    @Test
    @DisplayName("구입 금액만큼 로또를 발행한다.")
    void 구입_금액만큼_로또를_발행한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(8000);
        int countPublishLotto = purchaseAmount.countPublishLotto();

        NumberGenerator randomNumberGenerator = () -> List.of(1, 2, 3, 4, 5, 6);

        Lottos lottos = new Lottos();
        for (int count = 0; count < countPublishLotto; count++) {
            Lotto lotto = new Lotto(randomNumberGenerator.generate());
            lottos.add(lotto);
        }

        assertThat(lottos.size()).isEqualTo(countPublishLotto);
    }
}
