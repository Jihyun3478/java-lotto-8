package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lotto.model.domain.BonusNumber;
import lotto.model.domain.Lotto;
import lotto.model.domain.LottoMachine;
import lotto.model.domain.Lottos;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.Rank;
import lotto.model.domain.WinningNumber;
import lotto.model.domain.WinningStatistics;

public class RaceController {
    public void start() {
        PurchaseAmount purchaseAmount = createMoney();
        LottoMachine lottoMachine = new LottoMachine();
        Lottos lottos = new Lottos();

        int countPublishLotto = purchaseAmount.countPublishLotto();
        System.out.println("\n" + countPublishLotto + "개를 구매했습니다.");

        for (int count = 0; count < countPublishLotto; count++) {
            Lotto lotto = new Lotto(lottoMachine.generateRandomNumbers());
            lottos.add(lotto);

            String joinLottos = lotto.getLotto().stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println("[" + joinLottos + "]");
        }

        WinningNumber winningNumber = createWinningNumber();
        BonusNumber bonusNumber = createBonusNumber(winningNumber);

        int matchCount = 0;
        boolean isBonusMatched = false;

        Rank rank = null;
        WinningStatistics winningStatistics = new WinningStatistics();

        for (Lotto lotto : lottos.getLottos()) {
            matchCount = lotto.getMatchCount(winningNumber.getWinningNumber());
            isBonusMatched = lotto.isBonusMatched(bonusNumber.getBonusNumber());

            rank = Rank.getRank(matchCount, isBonusMatched);
            winningStatistics.add(rank);
        }
        getWinningStatistics(winningStatistics, purchaseAmount);
    }

    private PurchaseAmount createMoney() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                int purchaseAmount = getPurchaseAmount();

                return new PurchaseAmount(purchaseAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningNumber createWinningNumber() {
        while (true) {
            try {
                System.out.println("\n당첨 번호를 입력해 주세요.");
                List<Integer> winningNumber = getWinningNumber();

                return new WinningNumber(winningNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private BonusNumber createBonusNumber(WinningNumber winningNumber) {
        while (true) {
            try {
                System.out.println("\n보너스 번호를 입력해 주세요.");
                int bonusNumber = getBonusNumber();
                if (winningNumber.isDuplicateWithBonusNumber(bonusNumber)) {
                    throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복되지 않아야 합니다.");
                }

                return new BonusNumber(bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getPurchaseAmount() {
        try {
            String input = Console.readLine();
            if (Objects.isNull(input) || input.isBlank()) {
                throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    private void getWinningStatistics(WinningStatistics winningStatistics, PurchaseAmount purchaseAmount) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        for (Rank rank : Rank.values()) {
            if (rank.equals(Rank.SECOND)) {
                System.out.printf("%d개 일치, 보너스 볼 일치 (%,d원) - %d개%n", rank.getMatchCount(), rank.getWinningAmount(),
                        winningStatistics.getCountByRank(rank));
            } else {
                System.out.printf("%d개 일치 (%,d원) - %d개%n", rank.getMatchCount(), rank.getWinningAmount(),
                        winningStatistics.getCountByRank(rank));
            }
        }
        double prizePercent = winningStatistics.getPrizePercent(purchaseAmount);
        System.out.print("총 수익률은 " + prizePercent + "%입니다.");
    }

    private List<Integer> getWinningNumber() {
        try {
            String input = Console.readLine();
            if (Objects.isNull(input) || input.isBlank()) {
                throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
            }
            String[] splitInput = input.split(",", -1);
            return Arrays.stream(splitInput)
                    .map(String::strip)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
        }
    }

    private int getBonusNumber() {
        try {
            String input = Console.readLine();
            if (Objects.isNull(input) || input.isBlank()) {
                throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다.");
            }
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }
}
