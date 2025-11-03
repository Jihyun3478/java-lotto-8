package lotto.controller;

import lotto.handler.InputHandler;
import lotto.handler.OutputHandler;
import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.operation.WinningStatistics;
import lotto.model.service.LottoService;
import lotto.model.service.StatisticsService;

public class LottoController {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final LottoService lottoService;
    private final StatisticsService statisticsService;

    public LottoController(InputHandler inputHandler, OutputHandler outputHandler, LottoService lottoService,
                           StatisticsService statisticsService) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.lottoService = lottoService;
        this.statisticsService = statisticsService;
    }

    public void start() {
        PurchaseAmount purchaseAmount = inputHandler.handlePurchaseAmount();

        int countPublishLotto = purchaseAmount.countPublishLotto();
        outputHandler.displayLottoCount(countPublishLotto);

        Lottos lottos = lottoService.generateLottos(countPublishLotto);
        outputHandler.displayLottos(lottos);

        WinningNumber winningNumber = inputHandler.handleWinningNumber();
        BonusNumber bonusNumber = inputHandler.handleBonusNumber(winningNumber);

        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottos, winningNumber,
                bonusNumber);

        outputHandler.displayWinningStatistics(winningStatistics, purchaseAmount);
    }
}
