package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Objects;

public class Application {
    public static void main(String[] args) {
        PurchaseAmount purchaseAmount = createMoney();
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
}
