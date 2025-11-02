package lotto;

import static lotto.TestLottoNumber.*;

import java.util.List;
import lotto.model.numbers.LottoNumber;

public class TestLottoNumbers {

    public static final List<LottoNumber> BASIC_NUMBERS = List.of(ONE, TWO, THREE, FOUR, FIVE, SIX);
    public static final List<LottoNumber> MANY_NUMBERS = List.of(ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN);
    public static final List<LottoNumber> FEW_NUMBERS = List.of(ONE, TWO, THREE, FOUR, FIVE);
    public static final List<LottoNumber> DUPLICATE_NUMBERS = List.of(ONE, TWO, THREE, FOUR, FIVE, FIVE);
    public static final List<LottoNumber> UNSORTED_NUMBERS = List.of(SIX, FIVE, FOUR, THREE, TWO, ONE);
}
