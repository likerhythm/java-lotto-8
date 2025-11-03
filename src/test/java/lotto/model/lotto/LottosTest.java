package lotto.model.lotto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import lotto.ErrorMessage;
import lotto.dto.LottoResult;
import lotto.exception.LottoPurchaseException;
import lotto.model.numbers.DrawNumbers;
import lotto.model.numbers.LottoNumber;
import lotto.model.numbers.MainNumbersContainer;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

class LottosTest {

    private Lotto makeLotto(int... numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int n : numbers) {
            lottoNumbers.add(LottoNumber.of(n));
        }
        return new Lotto(new MainNumbersContainer(lottoNumbers));
    }

    private DrawNumbers makeDrawNumbers(List<Integer> winning, int bonus) {
        MainNumbersContainer main = new MainNumbersContainer(
                winning.stream().map(LottoNumber::of).toList());
        LottoNumber bonusNumber = LottoNumber.of(bonus);
        return new DrawNumbers(main, bonusNumber);
    }

    private Lottos makeCustomLottos(Lotto... lottosArray) {
        Lottos lottos = new Lottos(1000);
        try {
            Field field = Lottos.class.getDeclaredField("lottos");
            field.setAccessible(true);
            field.set(lottos, List.of(lottosArray));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lottos;
    }

    @Test
    void _1등_확인이_정상적으로_이루어진다() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 5, 6);
        Lottos lottos = makeCustomLottos(lotto);
        DrawNumbers drawNumbers = makeDrawNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = lottos.check(drawNumbers);
        assertThat(result.ranks().get(LottoRank._1ST)).isEqualTo(1);
    }

    @Test
    void _2등_확인이_정상적으로_이루어진다() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 5, 7);
        Lottos lottos = makeCustomLottos(lotto);
        DrawNumbers drawNumbers = makeDrawNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = lottos.check(drawNumbers);
        assertThat(result.ranks().get(LottoRank._2ND)).isEqualTo(1);
    }

    @Test
    void _3등_확인이_정상적으로_이루어진다() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 5, 8);
        Lottos lottos = makeCustomLottos(lotto);
        DrawNumbers drawNumbers = makeDrawNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = lottos.check(drawNumbers);
        assertThat(result.ranks().get(LottoRank._3RD)).isEqualTo(1);
    }

    @Test
    void _4등_확인이_정상적으로_이루어진다() {
        Lotto lotto = makeLotto(1, 2, 3, 4, 9, 10);
        Lottos lottos = makeCustomLottos(lotto);
        DrawNumbers drawNumbers = makeDrawNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = lottos.check(drawNumbers);
        assertThat(result.ranks().get(LottoRank._4TH)).isEqualTo(1);
    }

    @Test
    void _5등_확인이_정상적으로_이루어진다() {
        Lotto lotto = makeLotto(1, 2, 3, 11, 12, 13);
        Lottos lottos = makeCustomLottos(lotto);
        DrawNumbers drawNumbers = makeDrawNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = lottos.check(drawNumbers);
        assertThat(result.ranks().get(LottoRank._5TH)).isEqualTo(1);
    }

    @Test
    void 꽝_로또_확인이_정상적으로_이루어진다() {
        Lotto lotto = makeLotto(10, 11, 12, 13, 14, 15);
        Lottos lottos = makeCustomLottos(lotto);
        DrawNumbers drawNumbers = makeDrawNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = lottos.check(drawNumbers);
        assertThat(result.ranks().get(LottoRank._8TH)).isEqualTo(1);
    }

    @Test
    void 최대_개수_이상의_로또를_구매하려는_경우_예외가_발생한다() {
        int overLimitPrice = 1000 * 1001; // 1001장
        assertThatThrownBy(() -> new Lottos(overLimitPrice))
                .isInstanceOf(LottoPurchaseException.class)
                .hasMessage(ErrorMessage.EXCEED_MAX_LOTTO_COUNT.getMessage());
    }
}
