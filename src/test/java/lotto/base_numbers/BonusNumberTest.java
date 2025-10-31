package lotto.base_numbers;

import static lotto.TestLottoNumber.*;

import java.util.ArrayList;
import java.util.List;
import lotto.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BonusNumberTest {

    @Test
    void 보너스_번호는_하나만_존재합니다() {
        List<LottoNumber> numbers = new ArrayList<>(List.of(ONE, TWO));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BonusNumber(numbers));
    }
}
