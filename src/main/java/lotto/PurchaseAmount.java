package lotto;

public class PurchaseAmount {
    private int money;

    public PurchaseAmount(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        validateUnit(money);
        validateLowerLimit(money);
        validateUpperLimit(money);
    }

    private void validateUnit(int money) {
        if (money % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }

    private void validateLowerLimit(int money) {
        if (money < 1000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 이상이여야 합니다.");
        }
    }

    private void validateUpperLimit(int money) {
        if (money > 100000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 100,000원을 초과할 수 없습니다.");
        }
    }
}
