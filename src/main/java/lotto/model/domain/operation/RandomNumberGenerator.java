package lotto.model.domain.operation;

import static lotto.model.domain.operation.LottoMachine.LOTTO_SIZE;
import static lotto.model.domain.operation.LottoMachine.MAXIMUM_LOTTO_RANGE;
import static lotto.model.domain.operation.LottoMachine.MINIMUM_LOTTO_RANGE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(MINIMUM_LOTTO_RANGE, MAXIMUM_LOTTO_RANGE, LOTTO_SIZE);
    }
}
