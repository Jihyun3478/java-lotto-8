package lotto.model.domain.game;

import static lotto.common.constant.NumberConstant.LOTTO_SIZE;
import static lotto.common.constant.NumberConstant.MAXIMUM_LOTTO_RANGE;
import static lotto.common.constant.NumberConstant.MINIMUM_LOTTO_RANGE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(MINIMUM_LOTTO_RANGE, MAXIMUM_LOTTO_RANGE, LOTTO_SIZE);
    }
}
