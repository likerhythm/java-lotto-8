package lotto;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public String paymentPriceInputGuide() {
        System.out.println("구입금액을 입력해 주세요.");
        return readLine();
    }

    public String winningNumbersInputGuide() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return readLine();
    }

    public String bonusNumberInputGuide() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return readLine();
    }

    private static String readLine() {
        return Console.readLine();
    }
}
