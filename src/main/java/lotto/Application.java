package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Application {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = createMoney();
        WinningNumber winningNumber = createWinningNumber();
        BonusNumber bonusNumber = createBonusNumber(winningNumber);
    }

    private static PurchaseAmount createMoney() {
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

    private static WinningNumber createWinningNumber() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                List<Integer> winningNumber = getWinningNumber();

                return new WinningNumber(winningNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static BonusNumber createBonusNumber(WinningNumber winningNumber) {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
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

    private static int getPurchaseAmount() {
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

    private static List<Integer> getWinningNumber() {
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

    private static int getBonusNumber() {
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
