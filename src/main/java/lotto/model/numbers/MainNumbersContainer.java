package lotto.model.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.ErrorMessage;
import lotto.exception.LottoNumberException;

public class MainNumbersContainer {

    private static final int REQUIRED_QUANTITY = 6;

    private final List<LottoNumber> numbers;

    public MainNumbersContainer(List<LottoNumber> numbers) {
        List<LottoNumber> mutableNumbers = new ArrayList<>(numbers);
        Collections.sort(mutableNumbers);
        this.numbers = List.copyOf(mutableNumbers);
        validate(this.numbers);
    }

    public boolean contain(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    public int countContain(MainNumbersContainer other) {
        int count = 0;
        for (LottoNumber n : other.numbers) {
            if (this.numbers.contains(n)) count++;
        }
        return count;
    }

    @Override
    public String toString() {
        String str = numbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", "));
        return "[" + str + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MainNumbersContainer other)) {
            return false;
        }
        if (this.numbers.size() != other.numbers.size()) {
            return false;
        }
        return this.numbers.equals(other.numbers);
    }

    @Override
    public int hashCode() {
        return numbers.hashCode();
    }

    private void validate(List<LottoNumber> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new LottoNumberException(ErrorMessage.DUPLICATE_NUMBER_CONTAINER.getMessage());
        }
        if (numbers.size() != REQUIRED_QUANTITY) {
            throw new LottoNumberException(ErrorMessage.INVALID_LOTTO_NUMBER_COUNT.getMessage());
        }
    }
}
