package lotto.model.domain.game;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoMachine {
    public static final int LOTTO_SIZE = 6;
    public static final int MINIMUM_LOTTO_RANGE = 1;
    public static final int MAXIMUM_LOTTO_RANGE = 45;

    public List<Integer> generateRandomNumbers() {
        return Randoms.pickUniqueNumbersInRange(MINIMUM_LOTTO_RANGE, MAXIMUM_LOTTO_RANGE, LOTTO_SIZE);
    }
}

