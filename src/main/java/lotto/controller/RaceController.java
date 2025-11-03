package lotto.controller;

import java.util.List;
import java.util.stream.Collectors;
import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.game.Rank;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.game.WinningStatistics;
import lotto.model.service.LottoService;
import lotto.model.service.StatisticsService;
import lotto.util.InputParser;
import lotto.util.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class RaceController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;
    private final StatisticsService statisticsService;

    public RaceController(InputView inputView, OutputView outputView, LottoService lottoService,
                          StatisticsService statisticsService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
        this.statisticsService = statisticsService;
    }

    public void start() {
        PurchaseAmount purchaseAmount = createPurchaseAmount();

        int countPublishLotto = purchaseAmount.countPublishLotto();
        outputView.promptCountPublishLotto(countPublishLotto);

        Lottos lottos = lottoService.generateLottos(countPublishLotto);
        String formatLottos = formatLottos(lottos);
        outputView.promptLottos(formatLottos);

        WinningNumber winningNumber = createWinningNumber();
        BonusNumber bonusNumber = createBonusNumber(winningNumber);

        WinningStatistics winningStatistics = statisticsService.calculateWinningStatistics(lottos, winningNumber,
                bonusNumber);
        displayWinningStatistics(winningStatistics, purchaseAmount);
    }

    private PurchaseAmount createPurchaseAmount() {
        while (true) {
            try {
                outputView.requestPurchaseAmount();
                int purchaseAmount = getPurchaseAmount();

                return new PurchaseAmount(purchaseAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String formatLottos(Lottos lottos) {
        String joinLottos = "";
        for (Lotto lotto : lottos.getLottos()) {
            lotto.getLotto().stream().map(String::valueOf).collect(Collectors.joining(", "));
        }
        return joinLottos;
    }

    private WinningNumber createWinningNumber() {
        while (true) {
            try {
                outputView.requestWinningNumber();
                List<Integer> winningNumber = getWinningNumber();

                return new WinningNumber(winningNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber createBonusNumber(WinningNumber winningNumber) {
        while (true) {
            try {
                outputView.requestBonusNumber();
                int bonusNumber = getBonusNumber();
                if (winningNumber.isDuplicateWithBonusNumber(bonusNumber)) {
                    throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복되지 않아야 합니다.");
                }

                return new BonusNumber(bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getPurchaseAmount() {
        try {
            String input = inputView.input();
            InputValidator.validateNotBlank(input);

            return InputParser.parseToNumber(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    private List<Integer> getWinningNumber() {
        try {
            String input = inputView.input();
            InputValidator.validateNotBlank(input);

            return InputParser.parseToWinningNumber(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
        }
    }

    private int getBonusNumber() {
        try {
            String input = inputView.input();
            InputValidator.validateNotBlank(input);

            return InputParser.parseToNumber(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }

    private void displayWinningStatistics(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        for (Rank rank : Rank.values()) {
            if (rank.equals(Rank.SECOND)) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%,d원) - %d개%n", rank.getMatchCount(), rank.getWinningAmount(),
                        winningStatistics.getCountByRank(rank));
            } else {
                System.out.printf("%d개 일치 (%,d원) - %d개%n", rank.getMatchCount(), rank.getWinningAmount(),
                        winningStatistics.getCountByRank(rank));
            }
        }
        double prizePercent = winningStatistics.getPrizePercent(purchaseAmount);
        System.out.print("총 수익률은 " + prizePercent + "%입니다.");
    }
}
