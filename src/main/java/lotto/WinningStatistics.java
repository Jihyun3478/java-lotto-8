package lotto;

import java.util.EnumMap;
import java.util.Objects;

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
}
