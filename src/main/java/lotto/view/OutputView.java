package lotto.view;

import static lotto.view.OutputMessage.LOTTO_FORMAT;
import static lotto.view.OutputMessage.PROMPT_PURCHASE_COUNT;
import static lotto.view.OutputMessage.REQUEST_BONUS_NUMBER;
import static lotto.view.OutputMessage.REQUEST_PURCHASE_AMOUNT;
import static lotto.view.OutputMessage.REQUEST_WINNING_NUMBER;
import static lotto.view.OutputMessage.STATISTICS_HEADER;
import static lotto.view.OutputMessage.STATISTICS_PROFIT_RATE;
import static lotto.view.OutputMessage.STATISTICS_RANK_NORMAL;
import static lotto.view.OutputMessage.STATISTICS_RANK_SECOND;

import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.game.Rank;

public class OutputView {
    private static final String NEW_LINE = "\n";

    public void requestPurchaseAmount() {
        System.out.println(REQUEST_PURCHASE_AMOUNT.getMessage());
    }

    public void promptCountPublishLotto(int countPublishLotto) {
        System.out.println(NEW_LINE + PROMPT_PURCHASE_COUNT.getMessage(countPublishLotto));
    }

    public void promptLottos(Lottos lottos) {
        lottos.getLottos().forEach(this::formatLotto);
    }

    public void requestWinningNumber() {
        System.out.println(NEW_LINE + REQUEST_WINNING_NUMBER.getMessage());
    }

    public void requestBonusNumber() {
        System.out.println(NEW_LINE + REQUEST_BONUS_NUMBER.getMessage());
    }

    public void promptStatisticsHeader() {
        System.out.println(NEW_LINE + STATISTICS_HEADER.getMessage());
    }

    public void promptRanks(Rank rank, int countPublishLotto) {
        if (rank == Rank.SECOND) {
            System.out.println(STATISTICS_RANK_SECOND.getMessage(
                    rank.getMatchCount(), rank.getWinningAmount(), countPublishLotto
            ));
            return;
        }
        System.out.println(STATISTICS_RANK_NORMAL.getMessage(
                rank.getMatchCount(), rank.getWinningAmount(), countPublishLotto
        ));
    }

    public void promptProfitRate(double profitRate) {
        System.out.println(STATISTICS_PROFIT_RATE.getMessage(profitRate));
    }

    private void formatLotto(Lotto lotto) {
        String numbers = String.join(", ",
                lotto.getLotto().stream()
                        .map(String::valueOf)
                        .toList()
        );
        System.out.println(LOTTO_FORMAT.getMessage(numbers));
    }
}
