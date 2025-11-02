package lotto.model.numbers;

import lotto.ErrorMessage;

public class DrawNumbersBuilder {

    private MainNumbersContainer winningNumbers;
    private LottoNumber bonusNumber;

    public static DrawNumbersBuilder builder() {
        return new DrawNumbersBuilder();
    }

    public DrawNumbersBuilder winningNumbers(MainNumbersContainer winningNumbers) {
        this.winningNumbers = winningNumbers;
        return this;
    }

    public DrawNumbersBuilder bonusNumber(LottoNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
        return this;
    }

    public DrawNumbers build() {
        validate();
        return new DrawNumbers(this.winningNumbers, this.bonusNumber);
    }

    private void validate() {
        if (this.winningNumbers == null) {
            throw new IllegalStateException(ErrorMessage.INVALID_BUILD_STATE.getMessage());
        }
        if (this.bonusNumber == null) {
            throw new IllegalStateException(ErrorMessage.INVALID_BUILD_STATE.getMessage());
        }
    }
}
