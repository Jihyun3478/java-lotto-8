package lotto.view;

public class OutputView {
    public void requestPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void promptCountPublishLotto(int countPublishLotto) {
        System.out.println("\n" + countPublishLotto + "개를 구매했습니다.");
    }

    public void promptLottos(String joinLottos) {
        System.out.println("[" + joinLottos + "]");
    }

    public void requestWinningNumber() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
    }

    public void requestBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
    }
}
