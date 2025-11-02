package lotto.model.domain;

import static lotto.model.domain.game.LottoMachine.MAXIMUM_LOTTO_RANGE;
import static lotto.model.domain.game.LottoMachine.MINIMUM_LOTTO_RANGE;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateNumberRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < MINIMUM_LOTTO_RANGE || bonusNumber > MAXIMUM_LOTTO_RANGE) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1과 45 사이의 숫자여야 합니다.");
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
