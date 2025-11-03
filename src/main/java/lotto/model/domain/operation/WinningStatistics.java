package lotto.model.domain.operation;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Objects;
import lotto.model.domain.PurchaseAmount;

public class WinningStatistics {
    private final EnumMap<Rank, Integer> winningStatistics;

    public WinningStatistics() {
        this.winningStatistics = new EnumMap<>(Rank.class);
    }

    public void add(Rank rank) {
        if (Objects.nonNull(rank)) {
            winningStatistics.put(rank, winningStatistics.getOrDefault(rank, 0) + 1);
        }
    }

    public int getCountByRank(Rank rank) {
        return winningStatistics.getOrDefault(rank, 0);
    }

    public double getPrizePercent(PurchaseAmount purchaseAmount) {
        int sum = 0;
        for (Entry<Rank, Integer> entry : winningStatistics.entrySet()) {
            int winningAmount = Rank.getWinningAmount(entry.getKey());
            int count = entry.getValue();
            sum += winningAmount * count;
        }
        double prize = (double) sum / purchaseAmount.getPurchaseAmount();
        return Math.round(prize * 10000) / 100.0;
    }
}
