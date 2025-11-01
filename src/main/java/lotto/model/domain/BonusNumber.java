package lotto.model.domain;

public class BonusNumber {
    private final int bonusNumber;

    public BonusNumber(int bonusNumber) {
        validateNumberRange(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1과 45 사이의 숫자여야 합니다.");
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
