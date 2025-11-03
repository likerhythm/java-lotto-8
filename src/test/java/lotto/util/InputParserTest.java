package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.LottoPurchaseException;
import lotto.model.numbers.LottoNumber;
import org.junit.jupiter.api.Test;

class InputParserTest {

    @Test
    void 구입_금액을_정상적으로_정수로_파싱한다() {
        String input = "1000";
        int result = InputParser.parsePaymentPriceToCount(input);
        assertThat(result).isEqualTo(1000);
    }

    @Test
    void 구입_금액이_0_이하이면_예외를_발생시킨다() {
        String input = "0";
        assertThatThrownBy(() -> InputParser.parsePaymentPriceToCount(input))
                .isInstanceOf(LottoPurchaseException.class)
                .hasMessageContaining("구입 금액");
    }

    @Test
    void 추첨_번호를_쉼표_기준으로_파싱하여_LottoNumber_리스트로_변환한다() {
        String input = "1,2,3,4,5,6";
        List<LottoNumber> result = InputParser.parseWinningNumbers(input);
        assertThat(result).hasSize(6);
        assertThat(result).containsExactly(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        );
    }
}
