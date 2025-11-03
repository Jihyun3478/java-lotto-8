package lotto.controller;

import lotto.common.handler.InputHandler;
import lotto.common.handler.OutputHandler;
import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.game.WinningStatistics;
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

        Lottos lottos = getLottos(purchaseAmount.countPublishLotto());

        WinningStatistics winningStatistics = getWinningStatistics(lottos);
        outputHandler.displayWinningStatistics(winningStatistics, purchaseAmount);
    }

    private PurchaseAmount getPurchaseAmount() {
        PurchaseAmount purchaseAmount = inputHandler.handlePurchaseAmount();
        int countPublishLotto = purchaseAmount.countPublishLotto();
        outputHandler.displayLottoCount(countPublishLotto);

        return purchaseAmount;
    }

    private Lottos getLottos(int countPublishLotto) {
        Lottos lottos = lottoService.generateLottos(countPublishLotto);
        outputHandler.displayLottos(lottos);

        return lottos;
    }

    private WinningStatistics getWinningStatistics(Lottos lottos) {
        WinningNumber winningNumber = inputHandler.handleWinningNumber();
        BonusNumber bonusNumber = inputHandler.handleBonusNumber(winningNumber);

        return lottoService.calculateWinningStatistics(lottos, winningNumber,
                bonusNumber);
    }
}
