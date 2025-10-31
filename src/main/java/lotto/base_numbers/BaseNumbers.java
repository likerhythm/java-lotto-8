package lotto.base_numbers;

import java.util.Collections;
import java.util.List;
import lotto.LottoNumber;

public abstract class BaseNumbers {

    protected List<LottoNumber> numbers;

    public BaseNumbers(List<LottoNumber> numbers) {
        Collections.sort(numbers);
        this.numbers = List.copyOf(numbers);
    }

    public boolean contain(BaseNumbers other) {
        for (LottoNumber n : other.numbers) {
            if (!this.numbers.contains(n)) return false;
        }
        return true;
    }

    public int countContain(BaseNumbers other) {
        int count = 0;
        for (LottoNumber n : other.numbers) {
            if (this.numbers.contains(n)) count++;
        }
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BaseNumbers other)) {
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
}
