package lotto.base_numbers;

import java.util.List;
import lotto.LottoNumber;

public class UserNumbers extends BaseNumbers implements MainNumberContainer {

    public UserNumbers(List<LottoNumber> numbers) {
        super(numbers);
        validate(numbers);
    }
}
