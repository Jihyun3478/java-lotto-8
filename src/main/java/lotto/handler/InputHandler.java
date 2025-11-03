package lotto.handler;

import java.util.List;
import lotto.util.InputParser;
import lotto.util.InputValidator;
import lotto.model.domain.BonusNumber;
import lotto.model.domain.PurchaseAmount;
import lotto.model.domain.WinningNumber;
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

                return new BonusNumber(bonusNumber, winningNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getPurchaseAmount() {
        String input = inputView.input();
        InputValidator.validateNotBlank(input);

        return InputParser.parseNumber(input);
    }

    private List<Integer> getWinningNumber() {
        String input = inputView.input();
        InputValidator.validateNotBlank(input);

        return InputParser.parseWinningNumbers(input);
    }

    private int getBonusNumber() {
        String input = inputView.input();
        InputValidator.validateNotBlank(input);

        return InputParser.parseNumber(input);
    }
}
