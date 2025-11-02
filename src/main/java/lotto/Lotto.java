package lotto;

import lotto.base_numbers.MainNumbersContainer;
import lotto.draw_numbers.DrawNumbers;

public class Lotto {

    private static final int PRICE = 1000;

    private final MainNumbersContainer numbers;

    public Lotto(MainNumbersContainer numbers) {
        this.numbers = numbers;
    }

    public static int calculateLottoCount(int paymentPrice) {
        validateDivisible(paymentPrice);
        return paymentPrice / PRICE;
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

    private static void validateDivisible(int value) {
        if (value % Lotto.PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 로또 가격의 배수로 입력해주세요");
        }
    }
}
