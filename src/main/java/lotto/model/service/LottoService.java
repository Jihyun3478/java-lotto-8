package lotto.model.service;

import static lotto.common.constant.CommonConstant.PURCHASE_UNIT;

import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lotto;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.game.NumberGenerator;
import lotto.model.domain.game.Rank;
import lotto.model.domain.game.WinningStatistics;
import lotto.model.response.WinningStatisticsResponse;

public class LottoService {
    private final NumberGenerator numberGenerator;

    public LottoService(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos generateLottos(int purchaseAmount) {
        int lottoCount = getCount(purchaseAmount);

        Lottos lottos = new Lottos();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(numberGenerator.generate());
            lottos.add(lotto);
        }
        return lottos;
    }

    public WinningStatisticsResponse calculateResult(
            Lottos lottos,
            WinningNumber winningNumber,
            BonusNumber bonusNumber
    ) {
        WinningStatistics statistics = calculateWinningStatistics(
                lottos,
                winningNumber,
                bonusNumber
        );
        PurchaseAmount amount = createPurchaseAmount(lottos);

        return WinningStatisticsResponse.from(statistics, amount);
    }

    private int getCount(int purchaseAmount) {
        PurchaseAmount amount = new PurchaseAmount(purchaseAmount);
        return amount.countPublishLotto();
    }

    private WinningStatistics calculateWinningStatistics(
            Lottos lottos,
            WinningNumber winningNumber,
            BonusNumber bonusNumber
    ) {
        WinningStatistics statistics = new WinningStatistics();

        for (Lotto lotto : lottos.getLottos()) {
            int matchCount = lotto.getMatchCount(winningNumber.getWinningNumber());
            boolean isBonusMatched = lotto.isBonusMatched(bonusNumber.getBonusNumber());

            Rank rank = Rank.of(matchCount, isBonusMatched);
            statistics.add(rank);
        }
        return statistics;
    }

    private PurchaseAmount createPurchaseAmount(Lottos lottos) {
        int purchaseAmount = lottos.size() * PURCHASE_UNIT;
        return new PurchaseAmount(purchaseAmount);
    }
}
