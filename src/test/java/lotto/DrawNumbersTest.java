package lotto;

import static lotto.TestLottoNumber.*;

import java.util.ArrayList;
import java.util.List;
import lotto.base_numbers.BonusNumber;
import lotto.base_numbers.MainNumbersContainer;
import lotto.draw_numbers.DrawNumbersBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrawNumbersTest {

    @Test
    void 당첨_번호와_보너스_번호가_중복되는_경우_예외가_발생합니다() {
        List<LottoNumber> winning = new ArrayList<>(List.of(ONE, TWO, THREE, FOUR, FIVE, SIX));
        List<LottoNumber> bonus = new ArrayList<>(List.of(ONE));
        Assertions.assertThrows(IllegalArgumentException.class, () -> DrawNumbersBuilder.builder()
                .winningNumbers(new MainNumbersContainer(winning))
                .bonusNumber(new BonusNumber(bonus))
                .build());
    }
}
