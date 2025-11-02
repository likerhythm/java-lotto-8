package lotto;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static int parsePaymentPriceToCount(String input) {
        int paymentPrice = StringParser.toInteger(input, "구입 금액");
        validatePositive(paymentPrice);
        validateDivisible(paymentPrice);
        return paymentPrice / Lotto.PRICE;
    }

    public static List<LottoNumber> parseLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(str -> LottoNumber.of(StringParser.toInteger(str, "추첨 번호")))
                .toList();
    }

    public static LottoNumber parseBonusNumber(String input) {
        return LottoNumber.of(StringParser.toInteger(input, "보너스 번호"));
    }

    private static void validatePositive(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 양수로 입력해주세요");
        }
    }

    private static void validateDivisible(int value) {
        if (value % Lotto.PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 로또 가격의 배수로 입력해주세요");
        }
    }
}
