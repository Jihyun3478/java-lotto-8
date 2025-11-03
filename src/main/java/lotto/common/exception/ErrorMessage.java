package lotto.common.exception;

public enum ErrorMessage {
    INPUT_IS_EMPTY("입력값이 비어있습니다."),
    INPUT_MUST_NUMBER_FORMAT("입력값은 숫자여야 합니다."),

    LOTTO_INVALID_SIZE("로또 번호는 %d개여야 합니다."),
    LOTTO_MUST_NOT_DUPLICATE("로또 번호는 중복되지 않아야 합니다."),

    PURCHASE_AMOUNT_INVALID_UNIT("구입 금액은 %d원 단위여야 합니다."),
    PURCHASE_AMOUNT_MORE_THAN_THOUSAND("구입 금액은 %d원 이상이여야 합니다."),
    PURCHASE_AMOUNT_LESS_THAN_TEN_THOUSAND("구입 금액은 %,d원을 초과할 수 없습니다."),

    WINNING_NUMBER_INVALID_FORMAT("당첨 번호의 입력 형식이 올바르지 않습니다."),
    WINNING_NUMBER_INVALID_SIZE("당첨 번호는 %d개여야 합니다."),
    WINNING_NUMBER_INVALID_RANGE("당첨 번호는 %d과 %d 사이의 숫자여야 합니다."),
    WINNING_NUMBER_DUPLICATE("당첨 번호는 중복되지 않아야 합니다."),

    BONUS_NUMBER_MUST_NOT_DUPLICATE("보너스 번호는 당첨 번호와 중복되지 않아야 합니다."),
    BONUS_NUMBER_INVALID_RANGE("보너스 번호는 %d과 %d 사이의 숫자여야 합니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private static final String SUFFIX = " 다시 입력해 주세요.";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(Object... args) {
        return PREFIX + String.format(message, args) + SUFFIX;
    }
}
