package lotto.common.handler;

import java.util.List;
import lotto.model.domain.BonusNumber;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.WinningNumber;
import lotto.common.util.InputParser;
import lotto.common.util.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class InputHandler {
    private final InputView inputView;
    private final OutputView outputView;

    public InputHandler(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public PurchaseAmount handlePurchaseAmount() {
        while (true) {
            try {
                outputView.requestPurchaseAmount();
                int purchaseAmount = getPurchaseAmount();

                return new PurchaseAmount(purchaseAmount);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public WinningNumber handleWinningNumber() {
        while (true) {
            try {
                outputView.requestWinningNumber();
                List<Integer> winningNumber = getWinningNumber();

                return new WinningNumber(winningNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public BonusNumber handleBonusNumber(WinningNumber winningNumber) {
        while (true) {
            try {
                outputView.requestBonusNumber();
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
            String input = inputView.input();
            InputValidator.validateNotBlank(input);

            return InputParser.parseToNumber(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다.");
        }
    }

    private List<Integer> getWinningNumber() {
        try {
            String input = inputView.input();
            InputValidator.validateNotBlank(input);

            return InputParser.parseToWinningNumber(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
        }
    }

    private int getBonusNumber() {
        try {
            String input = inputView.input();
            InputValidator.validateNotBlank(input);

            return InputParser.parseToNumber(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }
}
