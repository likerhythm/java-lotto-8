package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.base_numbers.MainNumbersContainer;

public class LottoGenerator {

    private static final int LIMIT_QUANTITY = 1000; // TODO 현재 메모리양 고려한 로직 구현

    public static List<Lotto> generateLottos(int paymentPrice) {
        int count = Lotto.calculateLottoCount(paymentPrice);
        validate(count);
        List<Lotto> result = new ArrayList<>();
        while (count > 0) {
            Lotto lotto = generateLotto();
            boolean duplicated = result.stream().anyMatch(r -> r.equals(lotto));
            if (!duplicated) {
                count--;
                result.add(lotto);
            }
        }
        
        return result;
    }

    private static Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        List<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::of).toList();
        MainNumbersContainer lottoNumbersContainer = new MainNumbersContainer(lottoNumbers);
        return new Lotto(lottoNumbersContainer);
    }

    private static void validate(int count) {
        if (count > LottoGenerator.LIMIT_QUANTITY) {
            throw new IllegalArgumentException("[ERROR] 로또는 최대 " + LottoGenerator.LIMIT_QUANTITY +"개 구매할 수 있습니다");
        }
    }
}
