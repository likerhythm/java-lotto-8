package lotto.draw_numbers;

import lotto.base_numbers.BonusNumber;
import lotto.base_numbers.WinningNumbers;

public class DrawNumbers {

    private WinningNumbers winningNumbers;
    private BonusNumber bonusNumber;

    protected DrawNumbers(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validate();
    }

    protected void setWinningNumbers(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    protected void setBonusNumber(BonusNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    private void validate() {
        if (winningNumbers.contain(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복될 수 없습니다");
        }
    }
}
