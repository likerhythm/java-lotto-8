package lotto.model.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoNumberTest {

    @Test
    void 로또_번호_범위보다_크면_예외가_발생합니다() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(46));
    }

    @Test
    void 로또_번호_범위보다_작으면_예외가_발생합니다() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> LottoNumber.of(0));
    }
}
