package lotto.model.numbers;

import lotto.ErrorMessage;
import lotto.exception.LottoNumberException;

public class DrawNumbers {

    private final MainNumbersContainer winningNumbers;
    private final LottoNumber bonusNumber;

    public DrawNumbers(MainNumbersContainer winningNumbers, LottoNumber bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
        validate();
    }

    public int countWinningNumbersIn(MainNumbersContainer mainNumberContainer) {
        return mainNumberContainer.countContain(winningNumbers);
    }

    public boolean containBonusNumberIn(MainNumbersContainer mainNumbersContainer) {
        return mainNumbersContainer.contain(bonusNumber);
    }

    private void validate() {
        if (winningNumbers.contain(bonusNumber)) {
            throw new LottoNumberException(ErrorMessage.DUPLICATE_DRAW_NUMBERS.getMessage());
        }
    }
}
