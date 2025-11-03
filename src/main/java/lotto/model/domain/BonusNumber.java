package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.MINIMUM_NUMBER_RANGE;
import static lotto.common.exception.ErrorMessage.BONUS_NUMBER_INVALID_RANGE;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateNumberRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < MINIMUM_NUMBER_RANGE || bonusNumber > MAXIMUM_NUMBER_RANGE) {
            throw new IllegalArgumentException(BONUS_NUMBER_INVALID_RANGE.getMessage(MINIMUM_NUMBER_RANGE, MAXIMUM_NUMBER_RANGE));
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
