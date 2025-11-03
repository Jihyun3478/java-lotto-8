package lotto.model.domain;

import static lotto.common.constant.CommonConstant.NUMBERS_SIZE;
import static lotto.common.exception.ErrorMessage.LOTTO_INVALID_SIZE;
import static lotto.common.exception.ErrorMessage.LOTTO_MUST_NOT_DUPLICATE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class Lotto {
    private final List<Integer> lotto;

    public Lotto(List<Integer> lotto) {
        validate(lotto);
        this.lotto = sorted(lotto);
    }

    private void validate(List<Integer> lotto) {
        validateLottoSize(lotto);
        validateDuplicateNumber(lotto);
    }

    private void validateLottoSize(List<Integer> lotto) {
        if (lotto.size() != NUMBERS_SIZE) {
            throw new IllegalArgumentException(LOTTO_INVALID_SIZE.getMessage(NUMBERS_SIZE));
        }
    }

    private void validateDuplicateNumber(List<Integer> lotto) {
        Set<Integer> uniqueNumbers = new HashSet<>(lotto);
        if (lotto.size() != uniqueNumbers.size()) {
            throw new IllegalArgumentException(LOTTO_MUST_NOT_DUPLICATE.getMessage());
        }
    }

    public int size() {
        return lotto.size();
    }

    public int getMatchCount(List<Integer> winningNumber) {
        return Math.toIntExact(lotto.stream()
                .filter(number -> winningNumber.stream()
                        .anyMatch(Predicate.isEqual(number)))
                .count());
    }

    public boolean isBonusMatched(int bonusNumber) {
        return lotto.contains(bonusNumber);
    }

    public List<Integer> sorted(List<Integer> lotto) {
        return lotto.stream()
                .sorted()
                .toList();
    }

    public List<Integer> getLotto() {
        return lotto;
    }
}
