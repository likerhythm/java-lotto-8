package lotto;

import lotto.base_numbers.UserNumbers;
import lotto.draw_numbers.DrawNumbers;

public class Lotto {

    private final UserNumbers numbers;

    public Lotto(UserNumbers numbers) {
        this.numbers = numbers;
    }

    public int countWinningNumbers(DrawNumbers drawNumbers) {
        return drawNumbers.countWinningNumbersIn(numbers);
    }

    public boolean containBonusNumber(DrawNumbers drawNumbers) {
        return drawNumbers.containBonusNumberIn(numbers);
    }
}
