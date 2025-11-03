package lotto.model.domain;

import static lotto.common.constant.CommonConstant.MAXIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.MINIMUM_NUMBER_RANGE;
import static lotto.common.constant.CommonConstant.NUMBERS_SIZE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_DUPLICATE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_INVALID_RANGE;
import static lotto.common.exception.ErrorMessage.WINNING_NUMBER_INVALID_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumber {
    private final List<Integer> winningNumber;

    public WinningNumber(List<Integer> winningNumber) {
        validate(winningNumber);
        this.winningNumber = winningNumber;
    }

    public boolean isDuplicateWithBonusNumber(int bonusNumber) {
        return winningNumber.stream()
                .anyMatch(number -> number == bonusNumber);
    }

    private void validate(List<Integer> winningNumber) {
        validateSize(winningNumber);
        validateNumberRange(winningNumber);
        validateDuplicate(winningNumber);
    }

    private void validateSize(List<Integer> winningNumber) {
        if (winningNumber.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException(WINNING_NUMBER_INVALID_SIZE.getMessage(NUMBERS_SIZE));
        }
    }

    private void validateNumberRange(List<Integer> winningNumber) {
        for (int number : winningNumber) {
            if (number < MINIMUM_NUMBER_RANGE || number > MAXIMUM_NUMBER_RANGE) {
                throw new IllegalArgumentException(WINNING_NUMBER_INVALID_RANGE.getMessage(MINIMUM_NUMBER_RANGE, MAXIMUM_NUMBER_RANGE));
            }
        }
    }

    private void validateDuplicate(List<Integer> winningNumber) {
        Set<Integer> uniqueWinningNumbers = new HashSet<>(winningNumber);
        if (winningNumber.size() != uniqueWinningNumbers.size()) {
            throw new IllegalArgumentException(WINNING_NUMBER_DUPLICATE.getMessage());
        }
    }

    public List<Integer> getWinningNumber() {
        return winningNumber;
    }
}
