package lotto.base_numbers;

import java.util.List;
import lotto.LottoNumber;

public class BonusNumber extends BaseNumbers {

    public BonusNumber(List<LottoNumber> numbers) {
        super(numbers);
        validate(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != 1) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 하나만 존재할 수 있습니다");
        }
    }
}
