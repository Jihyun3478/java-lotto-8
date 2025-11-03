package lotto.model.response;

import lotto.model.domain.game.Rank;
import lotto.model.domain.game.WinningStatistics;

public record RankResponse(
        int matchCount,
        long winningAmount,
        int count,
        boolean isSecond
) {
    public static RankResponse from(Rank rank, WinningStatistics statistics) {
        return new RankResponse(
                rank.getMatchCount(),
                rank.getWinningAmount(),
                statistics.getCountByRank(rank),
                rank == Rank.SECOND
        );
    }
}
