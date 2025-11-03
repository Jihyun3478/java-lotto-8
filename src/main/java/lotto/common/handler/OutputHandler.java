package lotto.common.handler;

import lotto.model.domain.Lottos;
import lotto.model.response.WinningStatisticsResponse;
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

    public void displayWinningStatistics(WinningStatisticsResponse statisticsResponse) {
        outputView.displayStatistics(statisticsResponse);
    }
}
