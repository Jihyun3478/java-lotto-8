package lotto;

public class PurchaseAmount {
    private int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validate(int purchaseAmount) {
        validateUnit(purchaseAmount);
        validateLowerLimit(purchaseAmount);
        validateUpperLimit(purchaseAmount);
    }

    private void validateUnit(int purchaseAmount) {
        if (purchaseAmount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }

    private void validateLowerLimit(int purchaseAmount) {
        if (purchaseAmount < 1000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 이상이여야 합니다.");
        }
    }

    private void validateUpperLimit(int purchaseAmount) {
        if (purchaseAmount > 100000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 100,000원을 초과할 수 없습니다.");
        }
    }
}
