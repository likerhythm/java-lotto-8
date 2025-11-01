package lotto;

import static lotto.TestLottoNumber.*;
import static lotto.TestLottoNumbers.*;

import java.util.ArrayList;
import lotto.base_numbers.MainNumbersContainer;
import lotto.draw_numbers.DrawNumbers;
import lotto.draw_numbers.DrawNumbersBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        List<LottoNumber> numbers = new ArrayList<>(MANY_NUMBERS);
        assertThatThrownBy(() -> new Lotto(new MainNumbersContainer(numbers)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        List<LottoNumber> numbers = new ArrayList<>(DUPLICATE_NUMBERS);
        assertThatThrownBy(() -> new Lotto(new MainNumbersContainer(numbers)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에서_당첨_번호가_정상적으로_카운트됩니다() {
        MainNumbersContainer winningNumbers = new MainNumbersContainer(new ArrayList<>(BASIC_NUMBERS));
        DrawNumbers drawNumbers = DrawNumbersBuilder.builder()
                .winningNumbers(winningNumbers)
                .bonusNumber(SEVEN)
                .build();
        Lotto lotto = new Lotto(new MainNumbersContainer(new ArrayList<>(BASIC_NUMBERS)));
        Assertions.assertEquals(lotto.countWinningNumbers(drawNumbers), 6);
    }

    @Test
    void 로또_번호에서_보너스_번호가_정상적으로_인식됩니다() {
        MainNumbersContainer winningNumbers = new MainNumbersContainer(new ArrayList<>(BASIC_NUMBERS));
        DrawNumbers drawNumbers = DrawNumbersBuilder.builder()
                .winningNumbers(winningNumbers)
                .bonusNumber(SEVEN)
                .build();
        Lotto lotto = new Lotto(new MainNumbersContainer(new ArrayList<>(List.of(ONE, TWO, THREE, FOUR, FIVE, SEVEN))));
        Assertions.assertTrue(lotto.containBonusNumber(drawNumbers));
    }
}
