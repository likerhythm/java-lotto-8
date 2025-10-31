package lotto.base_numbers;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import lotto.LottoNumber;

public abstract class BaseNumbers {

    private static final int REQUIRED_QUANTITY = 6;

    protected List<LottoNumber> numbers;

    public BaseNumbers(List<LottoNumber> numbers) {
        Collections.sort(numbers);
        this.numbers = List.copyOf(numbers);
    }

    /**
     * 로또 번호 뽑을 때 사용
     * @param start
     * @param end
     * @param count
     * @return
     */
    public static List<Integer> pickUniqueInRange(int start, int end, int count) {
        return Randoms.pickUniqueNumbersInRange(start, end, count);
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

    protected void validate(List<LottoNumber> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 및 당첨 번호는 중복될 수 없습니다");
        }
        if (numbers.size() != REQUIRED_QUANTITY) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 및 당첨 번호는 " + REQUIRED_QUANTITY +"개입니다");
        }
    }
}
