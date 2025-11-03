package lotto.model.service;

import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.game.Rank;
import lotto.model.domain.game.WinningStatistics;

public class StatisticsService {
    public WinningStatistics calculateWinningStatistics(Lottos lottos, WinningNumber winningNumber, BonusNumber bonusNumber) {
        int matchCount = 0;
        boolean isBonusMatched = false;

        Rank rank = null;
        WinningStatistics winningStatistics = new WinningStatistics();

        for (Lotto lotto : lottos.getLottos()) {
            matchCount = lotto.getMatchCount(winningNumber.getWinningNumber());
            isBonusMatched = lotto.isBonusMatched(bonusNumber.getBonusNumber());

            rank = Rank.getRank(matchCount, isBonusMatched);
            winningStatistics.add(rank);
        }
        return winningStatistics;
    }
}
