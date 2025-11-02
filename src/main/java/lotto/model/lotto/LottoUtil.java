package lotto.model.lotto;

import lotto.ErrorMessage;
import lotto.exception.LottoPurchaseException;

public class LottoUtil {

    public static int calculateLottoCount(int paymentPrice) {
        validateDivisible(paymentPrice);
        return paymentPrice / Lotto.PRICE;
    }

    public static double calculateRateOfReturn(long totalReward, int lottoCount) {
        return (totalReward / (((double) Lotto.PRICE) * lottoCount)) * 100;
    }

    private static void validateDivisible(int value) {
        if (value % Lotto.PRICE != 0) {
            throw new LottoPurchaseException(ErrorMessage.INVALID_PAYMENT_PRICE.getMessage());
        }
    }
}
