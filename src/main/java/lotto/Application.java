package lotto;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.config.AppConfig;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        try {
            AppConfig appConfig = new AppConfig();
            LottoController lottoController = appConfig.lottoController();
            lottoController.start();
        } finally {
            Console.close();
        }
    }
}
