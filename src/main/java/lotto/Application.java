package lotto;

import lotto.controller.RaceController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        RaceController raceController = new RaceController(new InputView(), new OutputView());
        raceController.start();
    }
}
