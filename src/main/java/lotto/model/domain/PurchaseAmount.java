package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_PURCHASE_AMOUNT;
import static lotto.common.constant.CommonConstant.PURCHASE_UNIT;
import static lotto.common.exception.ErrorMessage.PURCHASE_AMOUNT_INVALID_UNIT;
import static lotto.common.exception.ErrorMessage.PURCHASE_AMOUNT_LESS_THAN_TEN_THOUSAND;
import static lotto.common.exception.ErrorMessage.PURCHASE_AMOUNT_MORE_THAN_THOUSAND;

public class PurchaseAmount {
    private final int purchaseAmount;

    public PurchaseAmount(int purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public int countPublishLotto() {
        return purchaseAmount / PURCHASE_UNIT;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    private void validate(int purchaseAmount) {
        validateUnit(purchaseAmount);
        validateLowerLimit(purchaseAmount);
        validateUpperLimit(purchaseAmount);
    }

    private void validateUnit(int purchaseAmount) {
        if (purchaseAmount % PURCHASE_UNIT != 0) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_INVALID_UNIT.getMessage(PURCHASE_UNIT));
        }
    }

    private void validateLowerLimit(int purchaseAmount) {
        if (purchaseAmount < PURCHASE_UNIT) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_MORE_THAN_THOUSAND.getMessage(PURCHASE_UNIT));
        }
    }

    private void validateUpperLimit(int purchaseAmount) {
        if (purchaseAmount > MAXIMUM_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(PURCHASE_AMOUNT_LESS_THAN_TEN_THOUSAND.getMessage(MAXIMUM_PURCHASE_AMOUNT));
        }
    }
}
