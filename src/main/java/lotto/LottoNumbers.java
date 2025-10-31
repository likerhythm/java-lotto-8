package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class LottoNumbers {

    public static List<Integer> pickUniqueInRange(int start, int end, int count) {
        return Randoms.pickUniqueNumbersInRange(start, end, count);
    }
}
