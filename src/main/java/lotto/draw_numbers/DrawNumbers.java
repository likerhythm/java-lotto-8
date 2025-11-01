package lotto.draw_numbers;

import lotto.base_numbers.BaseNumbers;
import lotto.base_numbers.BonusNumber;
import lotto.base_numbers.MainNumbersContainer;

public class DrawNumbers {

    private MainNumbersContainer winningNumbers;
    private BonusNumber bonusNumber;

    protected DrawNumbers(MainNumbersContainer winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validate();
    }

    public int countWinningNumbersIn(BaseNumbers baseNumbers) {
        return baseNumbers.countContain(winningNumbers);
    }

    public boolean containBonusNumberIn(BaseNumbers baseNumbers) {
        return baseNumbers.contain(bonusNumber);
    }

    private void validate() {
        if (winningNumbers.contain(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복될 수 없습니다");
        }
    }
}
