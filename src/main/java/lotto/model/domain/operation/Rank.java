package lotto.model.domain.operation;

import java.util.Arrays;

public enum Rank {
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    NONE(0, false, 0),
    ;

    private final int matchCount;
    private final boolean isBonusMatched;
    private final int winningAmount;

    Rank(int matchCount, boolean isBonusMatched, int winningAmount) {
        this.matchCount = matchCount;
        this.isBonusMatched = isBonusMatched;
        this.winningAmount = winningAmount;
    }

    public static Rank of(int matchCount, boolean isBonusMatched) {
        return Arrays.stream(values())
                .filter(rank -> rank.matches(matchCount, isBonusMatched))
                .findAny()
                .orElse(NONE);
    }

    private boolean matches(int matchCount, boolean isBonusMatched) {
        if (this.matchCount != matchCount) {
            return false;
        }
        return this.matchCount != 5 || this.isBonusMatched == isBonusMatched;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
