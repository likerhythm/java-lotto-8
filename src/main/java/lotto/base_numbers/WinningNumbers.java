package lotto.base_numbers;

import java.util.List;
import lotto.LottoNumber;

public class WinningNumbers extends BaseNumbers implements MainNumberContainer {

    public WinningNumbers(List<LottoNumber> numbers) {
        super(numbers);
        validate(numbers);
    }
}
