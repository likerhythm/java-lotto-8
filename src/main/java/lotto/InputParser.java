package lotto;

import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static int parsePaymentPriceToCount(String input) {
        int paymentPrice = StringParser.toInteger(input, "구입 금액");
        validatePositive(paymentPrice);
        return paymentPrice;
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
}
