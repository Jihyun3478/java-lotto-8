package lotto.model.service;

import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.game.LottoMachine;

public class LottoService {
    private LottoMachine lottoMachine = new LottoMachine();

    public Lottos generateLottos(int countPublishLotto) {
        Lottos lottos = new Lottos();
        for (int count = 0; count < countPublishLotto; count++) {
            Lotto lotto = new Lotto(lottoMachine.generateRandomNumbers());
            lottos.add(lotto);
        }
        return lottos;
    }
}
