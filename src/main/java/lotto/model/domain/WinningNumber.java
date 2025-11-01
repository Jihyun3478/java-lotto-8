package lotto.model.domain;

import static lotto.model.domain.LottoMachine.LOTTO_SIZE;
import static lotto.model.domain.LottoMachine.MAXIMUM_LOTTO_RANGE;
import static lotto.model.domain.LottoMachine.MINIMUM_LOTTO_RANGE;

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
        if (winningNumber.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
    }

    private void validateNumberRange(List<Integer> winningNumber) {
        for (int number : winningNumber) {
            if (number < MINIMUM_LOTTO_RANGE || number > MAXIMUM_LOTTO_RANGE) {
                throw new IllegalArgumentException("[ERROR] 당첨 번호는 1과 45 사이의 숫자여야 합니다.");
            }
        }
    }

    private void validateDuplicate(List<Integer> winningNumber) {
        Set<Integer> uniqueWinningNumbers = new HashSet<>(winningNumber);
        if (winningNumber.size() != uniqueWinningNumbers.size()) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복되지 않아야 합니다.");
        }
    }

    public List<Integer> getWinningNumber() {
        return winningNumber;
    }
}
