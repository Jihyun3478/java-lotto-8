package lotto.model.service;

import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.operation.Rank;
import lotto.model.domain.operation.WinningStatistics;

public class StatisticsService {
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
