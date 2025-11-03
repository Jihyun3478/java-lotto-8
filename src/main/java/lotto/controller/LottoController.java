package lotto.controller;

import lotto.handler.InputHandler;
import lotto.handler.OutputHandler;
import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.operation.WinningStatistics;
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
        PurchaseAmount purchaseAmount = inputHandler.handlePurchaseAmount();

        int countPublishLotto = purchaseAmount.countPublishLotto();
        outputHandler.displayLottoCount(countPublishLotto);

        Lottos lottos = lottoService.generateLottos(countPublishLotto);
        outputHandler.displayLottos(lottos);

        WinningNumber winningNumber = inputHandler.handleWinningNumber();
        BonusNumber bonusNumber = inputHandler.handleBonusNumber(winningNumber);

        WinningStatistics winningStatistics = lottoService.calculateWinningStatistics(lottos, winningNumber,
                bonusNumber);

        outputHandler.displayWinningStatistics(winningStatistics, purchaseAmount);
    }
}
