package lotto.util;

import java.util.Arrays;
import java.util.List;
import lotto.ErrorMessage;
import lotto.exception.LottoPurchaseException;
import lotto.model.numbers.LottoNumber;

public class InputParser {

    public static int parsePaymentPriceToCount(String input) {
        int paymentPrice = StringParser.toInteger(input);
        validatePositive(paymentPrice);
        return paymentPrice;
    }

    public static List<LottoNumber> parseWinningNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(str -> LottoNumber.of(StringParser.toInteger(str)))
                .toList();
    }

    public static LottoNumber parseBonusNumber(String input) {
        return LottoNumber.of(StringParser.toInteger(input));
    }

    private static void validatePositive(int value) {
        if (value <= 0) {
            throw new LottoPurchaseException(ErrorMessage.PAYMENT_PRICE_NOT_POSITIVE.getMessage());
        }
    }
}
