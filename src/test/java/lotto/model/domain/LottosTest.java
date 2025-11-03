package lotto.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import lotto.model.domain.operation.LottoMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {
    @Test
    @DisplayName("구입 금액만큼 로또를 발행한다.")
    void 구입_금액만큼_로또를_발행한다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(8000);
        int countPublishLotto = purchaseAmount.countPublishLotto();

        LottoMachine lottoMachine = new LottoMachine();
        Lottos lottos = new Lottos();

        for (int count = 0; count < countPublishLotto; count++) {
            Lotto lotto = new Lotto(lottoMachine.generateRandomNumbers());
            lottos.add(lotto);
        }

        assertEquals(lottos.size(), countPublishLotto);
    }
}
