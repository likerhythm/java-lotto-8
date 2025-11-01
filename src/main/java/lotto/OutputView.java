package lotto;

import java.util.List;

public class OutputView {

    public String printPurchasedLotto(List<String> purchasedLotto) {
        StringBuilder sb = new StringBuilder();
        for (String str : purchasedLotto) {
            sb.append(str).append("\n");
        }

        System.out.println(sb);
        return sb.toString();
    }

    public String printLottoResult(List<String> winningResult, String rateOfReturn) {
        StringBuilder sb = new StringBuilder();
        sb.append("당첨 통계\n").append("---\n");
        for (String str : winningResult) {
            sb.append(str).append("\n");
        }
        sb.append("총 수익률은 ").append(rateOfReturn).append("%입니다.");
        System.out.println(sb);
        return sb.toString();
    }

    public String printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
        return errorMessage;
    }
}
