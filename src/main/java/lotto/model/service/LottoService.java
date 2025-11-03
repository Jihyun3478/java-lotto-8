package lotto.model.service;

import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.game.NumberGenerator;
import lotto.model.domain.game.Rank;
import lotto.model.domain.game.WinningStatistics;

public class LottoService {
    private final NumberGenerator numberGenerator;

    public LottoService(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos generateLottos(int countPublishLotto) {
        Lottos lottos = new Lottos();
        for (int count = 0; count < countPublishLotto; count++) {
            Lotto lotto = new Lotto(numberGenerator.generate());
            lottos.add(lotto);
        }
        return lottos;
    }

    public WinningStatistics calculateWinningStatistics(Lottos lottos, WinningNumber winningNumber, BonusNumber bonusNumber) {
        WinningStatistics winningStatistics = new WinningStatistics();

        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = lotto.getMatchCount(winningNumber.getWinningNumber());
            boolean isBonusMatched = lotto.isBonusMatched(bonusNumber.getBonusNumber());

            Rank rank = Rank.of(matchCount, isBonusMatched);
            winningStatistics.add(rank);
        }
        return winningStatistics;
    }
}
