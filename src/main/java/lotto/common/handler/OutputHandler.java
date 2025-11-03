package lotto.common.handler;

import java.util.stream.Collectors;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.game.Rank;
import lotto.model.domain.game.WinningStatistics;
import lotto.view.OutputView;

public class OutputHandler {
    private final OutputView outputView;

    public OutputHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    public void displayLottoCount(int countPublishLotto) {
        outputView.promptCountPublishLotto(countPublishLotto);
    }

    public void displayLottos(Lottos lottos) {
        outputView.promptLottos(lottos);
    }

    public void displayWinningStatistics(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        outputView.promptStatisticsHeader();
        displayRanks(winningStatistics);
        displayProfitRate(winningStatistics, purchaseAmount);
    }

    private void displayRanks(WinningStatistics winningStatistics) {
        for (Rank rank : Rank.values()) {
            if (rank != Rank.NONE) {
                int count = winningStatistics.getCountByRank(rank);
                outputView.promptRanks(rank, count);
            }
        }
    }

    private void displayProfitRate(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        double prizePercent = winningStatistics.calculatePrizePercent(purchaseAmount);
        outputView.promptProfitRate(prizePercent);
    }
}
