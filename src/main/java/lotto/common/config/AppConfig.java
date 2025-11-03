package lotto.common.config;

import lotto.controller.LottoController;
import lotto.common.handler.InputHandler;
import lotto.common.handler.OutputHandler;
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

    public LottoService lottoService() {
        return new LottoService();
    }

    public LottoController lottoController() {
        return new LottoController(
                inputHandler(),
                outputHandler(),
                lottoService()
        );
    }
}
