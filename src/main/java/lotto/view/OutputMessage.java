package lotto.view;

public enum OutputMessage {
    REQUEST_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    REQUEST_WINNING_NUMBER("%n당첨 번호를 입력해 주세요."),
    REQUEST_BONUS_NUMBER("%n보너스 번호를 입력해 주세요."),

    PROMPT_PURCHASE_COUNT("%n%d개를 구매했습니다."),

    LOTTO_FORMAT("[%s]"),

    STATISTICS_HEADER("%n당첨 통계%n---"),
    STATISTICS_RANK_NORMAL("%d개 일치 (%,d원) - %d개"),
    STATISTICS_RANK_SECOND("%d개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    STATISTICS_PROFIT_RATE("총 수익률은 %.1f%%입니다."),
    ;

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return String.format(message, args);
    }
}
