package lotto.view;

public class OutputView {
    private static final String LINE_SEPARATOR = "\n";
    public void requestPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void promptCountPublishLotto(int countPublishLotto) {
        System.out.println(LINE_SEPARATOR + countPublishLotto + "개를 구매했습니다.");
    }

    public void promptLottos(String joinLottos) {
        System.out.println(joinLottos);
    }

    public void requestWinningNumber() {
        System.out.println(LINE_SEPARATOR + "당첨 번호를 입력해 주세요.");
    }

    public void requestBonusNumber() {
        System.out.println(LINE_SEPARATOR + "보너스 번호를 입력해 주세요.");
    }
}
