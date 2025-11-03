package lotto.model.response;

import java.util.Arrays;
import java.util.List;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.game.Rank;
import lotto.model.domain.game.WinningStatistics;

public record WinningStatisticsResponse(
        List<RankResponse> ranks,
        double profitRate
) {
    public static WinningStatisticsResponse from(
            WinningStatistics statistics,
            PurchaseAmount purchaseAmount
    ) {
        List<RankResponse> ranks = Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .map(rank -> RankResponse.from(rank, statistics))
                .toList();

        return new WinningStatisticsResponse(
                ranks,
                statistics.calculatePrizePercent(purchaseAmount)
        );
    }
}
