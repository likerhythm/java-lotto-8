package lotto.model.numbers;

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
            throw new IllegalArgumentException("[ERROR] 당첨 번호와 보너스 번호는 중복될 수 없습니다");
        }
    }
}
