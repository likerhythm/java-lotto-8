package lotto.view;

import java.util.List;
import lotto.singleton.Singleton;

@Singleton
public class OutputView {

    private static final String AFTER_PURCHASE_LOTTO_PHRASE = "%d개를 구매했습니다.\n%s\n";
    private static final String LOTTO_RESULT_PHRASE = "당첨 통계\n---\n%s\n총 수익률은 %s%%입니다.";

    private OutputView() {}

    public String printPurchasedLotto(List<String> purchasedLotto) {
        String result = String.format(
                AFTER_PURCHASE_LOTTO_PHRASE,
                purchasedLotto.size(),
                String.join("\n", purchasedLotto)
        );

        System.out.println(result);
        return result;
    }

    public String printLottoResult(List<String> winningResult, String rateOfReturn) {
        String result = String.format(
                LOTTO_RESULT_PHRASE,
                String.join("\n", winningResult),
                rateOfReturn
        );

        System.out.println(result);
        return result;
    }

    public String printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        return errorMessage;
    }
}
