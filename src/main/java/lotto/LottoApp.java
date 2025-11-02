package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.base_numbers.MainNumbersContainer;
import lotto.draw_numbers.DrawNumbers;
import lotto.lotto.Lotto;

public class LottoApp {

    private InputView inputView;
    private OutputView outputView;

    public LottoApp(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<Lotto> purchasedLotto = runWithRetry(this::getLottos);
        MainNumbersContainer winningNumbers = runWithRetry(this::getWinningNumbers);
        DrawNumbers drawNumbers = runWithRetry(() -> getBonusNumberAndBuildDrawNumbers(winningNumbers));
        LottoResult lottoResult = runWithRetry(() -> getLottoResult(drawNumbers, purchasedLotto));

        List<String> winningResult = makeWinningResult(lottoResult);
        outputView.printLottoResult(winningResult, String.valueOf(lottoResult.rateOfReturn()));
    }

    private List<Lotto> getLottos() {
        String input = inputView.paymentPriceInputGuide();
        int paymentPrice = InputParser.parsePaymentPriceToCount(input);
        List<Lotto> purchasedLotto = LottoGenerator.generateLottos(paymentPrice);
        outputView.printPurchasedLotto(purchasedLotto.stream().map(Lotto::toString).toList());
        return purchasedLotto;
    }

    private MainNumbersContainer getWinningNumbers() {
        String input;
        input = inputView.winningNumbersInputGuide();
        List<LottoNumber> winningNumbers = InputParser.parseLottoNumbers(input);
        return new MainNumbersContainer(winningNumbers);
    }

    private DrawNumbers getBonusNumberAndBuildDrawNumbers(MainNumbersContainer winningNumbers) {
        String input;
        input = inputView.bonusNumberInputGuide();
        LottoNumber bonusNumber = InputParser.parseBonusNumber(input);
        return new DrawNumbers(winningNumbers, bonusNumber);
    }

    private LottoResult getLottoResult(DrawNumbers drawNumbers, List<Lotto> purchasedLotto) {
        LottoVerificator lottoVerificator = new LottoVerificator(drawNumbers, purchasedLotto);
        return lottoVerificator.check();
    }

    private List<String> makeWinningResult(LottoResult lottoResult) {
        Map<LottoRank, Integer> ranks = lottoResult.ranks();
        List<String> winningResult = new ArrayList<>();
        for (Map.Entry<LottoRank, Integer> entry : ranks.entrySet()) {
            LottoRank rank = entry.getKey();
            if (!rank.isWinner()) continue;
            int count = entry.getValue();
            winningResult.add(rank + " - " + StringParser.numberFormat(count) + "개");
        }
        return winningResult;
    }

    private <T> T runWithRetry(Supplier<T> task) {
        return RetryExecutor.runWithRetry(task);
    }
}
