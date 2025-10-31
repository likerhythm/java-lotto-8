package lotto.base_numbers;

import java.util.List;
import lotto.LottoNumber;

public class WinningNumbers extends BaseNumbers {

    public WinningNumbers(List<LottoNumber> numbers) {
        super(numbers);
        validate(numbers);
    }

    @Override
    protected void validate(List<LottoNumber> numbers) {
        super.validate(numbers);
    }
}
