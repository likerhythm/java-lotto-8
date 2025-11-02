package lotto.model.numbers;

import static lotto.TestLottoNumbers.BASIC_NUMBERS;
import static lotto.TestLottoNumbers.DUPLICATE_NUMBERS;
import static lotto.TestLottoNumbers.FEW_NUMBERS;
import static lotto.TestLottoNumbers.MANY_NUMBERS;
import static lotto.TestLottoNumbers.UNSORTED_NUMBERS;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainNumbersContainerTest {

    @Test
    void 사용자_로또_번호는_6개_입니다() {
        List<LottoNumber> numbers = new ArrayList<>(BASIC_NUMBERS);
        Assertions.assertDoesNotThrow(() -> new MainNumbersContainer(numbers));
    }

    @Test
    void 사용자_로또_번호가_6개보다_적은_경우_예외가_발생합니다() {
        List<LottoNumber> numbers = new ArrayList<>(FEW_NUMBERS);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MainNumbersContainer(numbers));
    }

    @Test
    void 사용자_로또_번호가_6개보다_많은_경우_예외가_발생합니다() {
        List<LottoNumber> numbers = new ArrayList<>(MANY_NUMBERS);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MainNumbersContainer(numbers));
    }

    @Test
    void 사용자_로또_번호에_중복이_존재하는_경우_예외가_발생합니다() {
        List<LottoNumber> numbers = new ArrayList<>(DUPLICATE_NUMBERS);
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MainNumbersContainer(numbers));
    }

    @Test
    void 사용자_로또_번호는_오름차순으로_관리됩니다() {
        List<LottoNumber> numbers = new ArrayList<>(UNSORTED_NUMBERS);
        MainNumbersContainer userNumbers = new MainNumbersContainer(numbers);
        Assertions.assertEquals(new MainNumbersContainer(new ArrayList<>(BASIC_NUMBERS)), userNumbers);
    }
}
