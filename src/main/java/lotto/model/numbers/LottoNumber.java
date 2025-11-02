package lotto.model.numbers;

import lotto.ErrorMessage;
import lotto.exception.LottoNumberException;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private final int value;

    private LottoNumber(int value) {
        this.value = value;
        validate(value);
    }

    public static LottoNumber of(int value) {
        return new LottoNumber(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int compareTo(LottoNumber other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof LottoNumber)) {
            return false;
        }
        return this.value == ((LottoNumber) other).value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    private void validate(int number) {
        if (!inRange(number)) {
            throw new LottoNumberException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.getMessage());
        }
    }

    private static boolean inRange(int number) {
        return MIN <= number && number <= MAX;
    }
}
