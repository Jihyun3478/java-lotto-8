package lotto.common.config;

import lotto.controller.LottoController;
import lotto.handler.InputHandler;
import lotto.handler.OutputHandler;
import lotto.model.domain.game.NumberGenerator;
import lotto.model.domain.game.RandomNumberGenerator;
import lotto.model.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class AppConfig {
    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public InputHandler inputHandler() {
        return new InputHandler(inputView(), outputView());
    }

    public OutputHandler outputHandler() {
        return new OutputHandler(outputView());
    }

    public NumberGenerator numberGenerator() {
        return new RandomNumberGenerator();
    }

    public LottoService lottoService() {
        return new LottoService(numberGenerator());
    }

    public LottoController lottoController() {
        return new LottoController(
                inputHandler(),
                outputHandler(),
                lottoService()
        );
    }
}
