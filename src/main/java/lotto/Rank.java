package lotto;

public enum Rank {
    FIFTH(3, false, 5_000),
    FOURTH(4, false, 50_000),
    THIRD(5, false, 1_500_000),
    SECOND(5, true, 30_000_000),
    FIRST(6, false, 2_000_000_000),
    ;

    private final int matchCount;
    private final boolean isBonusMatched;
    private final int winningAmount;

    Rank(int matchCount, boolean isBonusMatched, int winningAmount) {
        this.matchCount = matchCount;
        this.isBonusMatched = isBonusMatched;
        this.winningAmount = winningAmount;
    }

    public static Rank getRank(int matchCount, boolean isBonusMatched) {
        for (Rank rank : Rank.values()) {
            if (rank.matchCount == matchCount) {
                if (rank.matchCount == 5 && rank.isBonusMatched == isBonusMatched) {
                    return SECOND;
                }
                return rank;
            }
        }
        return null;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getWinningAmount() {
        return winningAmount;
    }
}
