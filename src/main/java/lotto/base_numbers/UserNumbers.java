package lotto.base_numbers;

import java.util.List;
import lotto.LottoNumber;

public class UserNumbers extends BaseNumbers {

    public UserNumbers(List<LottoNumber> numbers) {
        super(numbers);
        validate(numbers);
    }

    @Override
    public void validate(List<LottoNumber> numbers) {
        super.validate(numbers);
    }
}
