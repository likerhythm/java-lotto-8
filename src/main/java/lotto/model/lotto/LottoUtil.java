package lotto.model.lotto;

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
            throw new LottoPurchaseException("[ERROR] 구입 금액은 로또 가격의 배수로 입력해주세요");
        }
    }
}
