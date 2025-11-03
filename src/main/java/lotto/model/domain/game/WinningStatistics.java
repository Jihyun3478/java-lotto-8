package lotto.model.domain.game;

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

    public double calculatePrizePercent(PurchaseAmount purchaseAmount) {
        double prize = calculateTotalPrize() / purchaseAmount.getPurchaseAmount();
        return Math.round(prize * 10000) / 100.0;
    }

    private double calculateTotalPrize() {
        return winningStatistics.entrySet().stream()
                .mapToDouble(this::calculateRankPrize)
                .sum();
    }

    private double calculateRankPrize(Entry<Rank, Integer> entry) {
        Rank rank = entry.getKey();
        long winningAmount = rank.getWinningAmount();
        int count = entry.getValue();

        return winningAmount * count;
    }
}
