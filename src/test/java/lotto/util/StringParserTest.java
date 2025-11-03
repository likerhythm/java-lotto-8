package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.ErrorMessage;
import org.junit.jupiter.api.Test;

class StringParserTest {

    @Test
    void 문자열을_정수로_정상적으로_변환한다() {
        int result = StringParser.toInteger("1234");
        assertThat(result).isEqualTo(1234);
    }

    @Test
    void null_입력_시_예외를_발생시킨다() {
        assertThatThrownBy(() -> StringParser.toInteger(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.UNKNOWN_ERROR.getMessage());
    }

    @Test
    void 숫자가_아닌_문자열_입력_시_예외를_발생시킨다() {
        assertThatThrownBy(() -> StringParser.toInteger("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_INTEGER.getMessage());
    }

    @Test
    void 정수를_천_단위_구분_기호로_포맷한다() {
        String formatted = StringParser.numberFormat(1234567);
        assertThat(formatted).isEqualTo("1,234,567");
    }

    @Test
    void 실수를_소수점_한_자리와_천_단위_구분_기호로_포맷한다() {
        String formatted = StringParser.numberFormat(1234567.89);
        assertThat(formatted).isEqualTo("1,234,567.9");
    }
}
