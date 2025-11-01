package lotto.model.domain;

public class PurchaseAmount {
    private static final int PURCHASE_UNIT = 1000;
    private static final int MAXIMUM_PURCHASE_AMOUNT = 100000;

    private int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public int countPublishLotto() {
        return purchaseAmount / PURCHASE_UNIT;
    }

    private void validate(int purchaseAmount) {
        validateUnit(purchaseAmount);
        validateLowerLimit(purchaseAmount);
        validateUpperLimit(purchaseAmount);
    }

    private void validateUnit(int purchaseAmount) {
        if (purchaseAmount % PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }

    private void validateLowerLimit(int purchaseAmount) {
        if (purchaseAmount < PURCHASE_UNIT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 이상이여야 합니다.");
        }
    }

    private void validateUpperLimit(int purchaseAmount) {
        if (purchaseAmount > MAXIMUM_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 100,000원을 초과할 수 없습니다.");
        }
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }
}
