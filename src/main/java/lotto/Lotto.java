package lotto;

import lotto.base_numbers.MainNumbersContainer;
import lotto.draw_numbers.DrawNumbers;

public class Lotto {

    private final MainNumbersContainer numbers;

    public Lotto(MainNumbersContainer numbers) {
        this.numbers = numbers;
    }

    public int countWinningNumbers(DrawNumbers drawNumbers) {
        return drawNumbers.countWinningNumbersIn(numbers);
    }

    public boolean containBonusNumber(DrawNumbers drawNumbers) {
        return drawNumbers.containBonusNumberIn(numbers);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lotto other)) {
            return false;
        }

        return this.numbers.equals(other.numbers);
    }

    @Override
    public int hashCode() {
        return this.numbers.hashCode();
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
