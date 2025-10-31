package lotto.base_numbers;

import static lotto.TestLottoNumbers.BASIC_NUMBERS;
import static lotto.TestLottoNumbers.DUPLICATE_NUMBERS;
import static lotto.TestLottoNumbers.FEW_NUMBERS;
import static lotto.TestLottoNumbers.MANY_NUMBERS;

import java.util.ArrayList;
import java.util.List;
import lotto.LottoNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WinningNumbersTest {

    @Test
    void 당첨_번호는_6개_입니다() {
        List<LottoNumber> numbers = new ArrayList<>(BASIC_NUMBERS);
        Assertions.assertDoesNotThrow(() -> new UserNumbers(numbers));
    }

    @Test
    void 당첨_번호가_6개보다_적은_경우_예외가_발생합니다() {
        List<LottoNumber> numbers = new ArrayList<>(FEW_NUMBERS);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserNumbers(numbers));
    }

    @Test
    void 당첨_번호가_6개보다_많은_경우_예외가_발생합니다() {
        List<LottoNumber> numbers = new ArrayList<>(MANY_NUMBERS);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserNumbers(numbers));
    }

    @Test
    void 당첨_번호에_중복이_존재하는_경우_예외가_발생합니다() {
        List<LottoNumber> numbers = new ArrayList<>(DUPLICATE_NUMBERS);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserNumbers(numbers));
    }
}
