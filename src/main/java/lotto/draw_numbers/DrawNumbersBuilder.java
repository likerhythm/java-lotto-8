package lotto.draw_numbers;

import lotto.base_numbers.BonusNumber;
import lotto.base_numbers.WinningNumbers;

public class DrawNumbersBuilder {

    private WinningNumbers winningNumbers;
    private BonusNumber bonusNumber;

    public static DrawNumbersBuilder builder() {
        return new DrawNumbersBuilder();
    }

    public DrawNumbersBuilder winningNumbers(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
        return this;
    }

    public DrawNumbersBuilder bonusNumber(BonusNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
        return this;
    }

    public DrawNumbers build() {
        validate();
        return new DrawNumbers(this.winningNumbers, this.bonusNumber);
    }

    private void validate() {
        if (this.winningNumbers == null) {
            throw new IllegalStateException("[ERROR] 당첨 번호가 등록되지 않았습니다");
        }
        if (this.bonusNumber == null) {
            throw new IllegalStateException("[ERROR] 보너스 번호가 등록되지 않았습니다");
        }
    }
}
