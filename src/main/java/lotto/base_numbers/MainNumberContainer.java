package lotto.base_numbers;

import java.util.HashSet;
import java.util.List;
import lotto.LottoNumber;

public interface MainNumberContainer {

    int REQUIRED_QUANTITY = 6;

    default void validate(List<LottoNumber> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 및 당첨 번호는 중복될 수 없습니다");
        }
        if (numbers.size() != REQUIRED_QUANTITY) {
            throw new IllegalArgumentException("[ERROR] 로또 번호 및 당첨 번호는 " + REQUIRED_QUANTITY +"개입니다");
        }
    }
}
