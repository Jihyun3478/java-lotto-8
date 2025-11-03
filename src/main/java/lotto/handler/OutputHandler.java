package lotto.handler;

import java.util.stream.Collectors;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.operation.Rank;
import lotto.model.domain.operation.WinningStatistics;
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
        outputView.promptLottos(formatLottos(lottos));
    }

    public void displayWinningStatistics(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        displayHeader();
        displayRanks(winningStatistics);
        displayProfitRate(winningStatistics, purchaseAmount);
    }

    private String formatLottos(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(this::formatSingleLotto)
                .collect(Collectors.joining("\n"));
    }

    private String formatSingleLotto(Lotto lotto) {
        return "[" + lotto.getLotto().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", ")) + "]";
    }

    private void displayHeader() {
        System.out.println("\n당첨 통계");
        System.out.println("---");
    }

    private void displayRanks(WinningStatistics winningStatistics) {
        for (Rank rank : Rank.values()) {
            if (rank.equals(Rank.SECOND)) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%,d원) - %d개%n", rank.getMatchCount(), rank.getWinningAmount(),
                        winningStatistics.getCountByRank(rank));
            } else {
                System.out.printf("%d개 일치 (%,d원) - %d개%n", rank.getMatchCount(), rank.getWinningAmount(),
                        winningStatistics.getCountByRank(rank));
            }
        }
    }

    private void displayProfitRate(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        double prizePercent = winningStatistics.getPrizePercent(purchaseAmount);
        System.out.print("총 수익률은 " + prizePercent + "%입니다.");
    }
}
