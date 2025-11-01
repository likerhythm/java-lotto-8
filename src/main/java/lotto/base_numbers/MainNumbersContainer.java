package lotto.base_numbers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lotto.LottoNumber;

public class MainNumbersContainer {

    private static final int REQUIRED_QUANTITY = 6;

    private List<LottoNumber> numbers;

    public MainNumbersContainer(List<LottoNumber> numbers) {
        Collections.sort(numbers);
        this.numbers = List.copyOf(numbers);
        validate(numbers);
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
            throw new IllegalArgumentException("[ERROR] 로또 번호 및 당첨 번호는 중복될 수 없습니다");
        }
        if (numbers.size() != REQUIRED_QUANTITY) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 및 당첨 번호는 " + REQUIRED_QUANTITY +"개입니다");
        }
    }
}
