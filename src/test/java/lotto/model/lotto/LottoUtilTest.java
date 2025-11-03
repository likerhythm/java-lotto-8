package lotto.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.ErrorMessage;
import lotto.exception.LottoPurchaseException;
import org.junit.jupiter.api.Test;

class LottoUtilTest {

    @Test
    void calculateLottoCount_정상동작() {
        int paymentPrice = 14000;
        int count = LottoUtil.calculateLottoCount(paymentPrice);
        assertThat(count).isEqualTo(14);
    }

    @Test
    void calculateLottoCount_예외발생() {
        int invalidPrice = 1500;
        assertThatThrownBy(() -> LottoUtil.calculateLottoCount(invalidPrice))
                .isInstanceOf(LottoPurchaseException.class)
                .hasMessage(ErrorMessage.INVALID_PAYMENT_PRICE.getMessage());
    }

    @Test
    void calculateRateOfReturn_정상동작() {
        long totalReward = 5000;
        int lottoCount = 2;
        double rate = LottoUtil.calculateRateOfReturn(totalReward, lottoCount);

        assertThat(rate).isEqualTo(250.0);
    }

    @Test
    void calculateRateOfReturn_0당첨금() {
        long totalReward = 0;
        int lottoCount = 5;
        double rate = LottoUtil.calculateRateOfReturn(totalReward, lottoCount);

        assertThat(rate).isEqualTo(0.0);
    }
}
