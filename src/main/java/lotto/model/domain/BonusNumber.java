package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.MINIMUM_NUMBER_RANGE;
import static lotto.common.exception.ErrorMessage.BONUS_NUMBER_INVALID_RANGE;
import static lotto.common.exception.ErrorMessage.BONUS_NUMBER_MUST_NOT_DUPLICATE;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber, WinningNumber winningNumber) {
        validate(bonusNumber, winningNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validate(int bonusNumber, WinningNumber winningNumber) {
        validateNumberRange(bonusNumber);
        validateDuplicateWinningNumber(bonusNumber, winningNumber);
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < MINIMUM_NUMBER_RANGE || bonusNumber > MAXIMUM_NUMBER_RANGE) {
            throw new IllegalArgumentException(BONUS_NUMBER_INVALID_RANGE.getMessage(MINIMUM_NUMBER_RANGE, MAXIMUM_NUMBER_RANGE));
        }
    }

    private void validateDuplicateWinningNumber(int bonusNumber, WinningNumber winningNumber) {
        if (winningNumber.isContainBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_MUST_NOT_DUPLICATE.getMessage());
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
