package lotto;

public enum ErrorMessage {

    INVALID_PAYMENT_PRICE("구입 가격은 로또 가격의 배수로 입력해주세요."),
    EXCEED_MAX_LOTTO_COUNT("구매할 수 있는 최대 로또 수를 초과했습니다."),
    PAYMENT_PRICE_NOT_POSITIVE("구입 금액은 양수로 입력해주세요."),

    DUPLICATE_DRAW_NUMBERS("당첨 번호와 보너스 번호는 중복될 수 없습니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호 범위를 초과하였습니다."),
    DUPLICATE_NUMBER_CONTAINER("로또 번호/당첨 번호는 중복된 수로 이루어질 수 없습니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호/당첨 번호는 6개입니다."),

    INVALID_BUILD_STATE("아직 객체를 빌드할 준비가 되지 않았습니다."),
    EXCEED_MAX_RETRY_ATTEMPT("최대 재시도 횟수를 초과했습니다. 처음부터 다시 시도해주세요."),
    INVALID_INTEGER("정수를 입력해주세요."),
    UNKNOWN_ERROR("알 수 없는 오류가 발생했습니다"),

    FAIL_TO_REGISTER_SINGLETON_INSTANCE("싱글톤 인스턴스 생성에 실패했습니다")
    ;

    private static final String PREFIX = "[ERROR] ";

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
