package lotto.controller;

import lotto.handler.InputHandler;
import lotto.handler.OutputHandler;
import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.WinningNumber;
import lotto.model.response.WinningStatisticsResponse;
import lotto.model.service.LottoService;

public class LottoController {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final LottoService lottoService;

    public LottoController(InputHandler inputHandler, OutputHandler outputHandler, LottoService lottoService) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.lottoService = lottoService;
    }

    public void start() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        Lottos lottos = getLottos(purchaseAmount);

        WinningStatisticsResponse statisticsResponse = getGameResult(lottos);
        outputHandler.displayWinningStatistics(statisticsResponse);
    }

    private PurchaseAmount getPurchaseAmount() {
        PurchaseAmount purchaseAmount = inputHandler.handlePurchaseAmount();
        outputHandler.displayLottoCount(purchaseAmount.countPublishLotto());

        return purchaseAmount;
    }

    private Lottos getLottos(PurchaseAmount purchaseAmount) {
        Lottos lottos = lottoService.generateLottos(purchaseAmount.getPurchaseAmount());
        outputHandler.displayLottos(lottos);

        return lottos;
    }

    private WinningStatisticsResponse getGameResult(Lottos lottos) {
        WinningNumber winningNumber = inputHandler.handleWinningNumber();
        BonusNumber bonusNumber = inputHandler.handleBonusNumber(winningNumber);

        return lottoService.calculateResult(lottos, winningNumber, bonusNumber);
    }
}
