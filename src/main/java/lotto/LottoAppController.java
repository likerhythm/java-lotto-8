package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.dto.LottoResult;
import lotto.model.lotto.LottoRank;
import lotto.model.numbers.LottoNumber;
import lotto.model.numbers.MainNumbersContainer;
import lotto.model.numbers.DrawNumbers;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.Lottos;
import lotto.model.LottoMatcher;
import lotto.util.InputParser;
import lotto.util.RetryExecutor;
import lotto.util.StringParser;
import lotto.view.InputView;
import lotto.view.OutputView;

@Singleton
public class LottoAppController {

    private final InputView inputView;
    private final OutputView outputView;

    private LottoAppController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Lottos lottos = runWithRetry(this::getLottos);
        MainNumbersContainer winningNumbers = runWithRetry(this::getWinningNumbers);
        DrawNumbers drawNumbers = runWithRetry(() -> getBonusNumberAndBuildDrawNumbers(winningNumbers));
        LottoResult lottoResult = runWithRetry(() -> lottos.check(drawNumbers));

        List<String> winningResult = makeWinningResult(lottoResult);
        outputView.printLottoResult(winningResult, StringParser.numberFormat(lottoResult.rateOfReturn()));
    }

    private Lottos getLottos() {
        String input = inputView.paymentPriceInputGuide();
        int paymentPrice = InputParser.parsePaymentPriceToCount(input);
        Lottos lottos = new Lottos(paymentPrice);
        outputView.printPurchasedLotto(lottos.getLottoInfo());
        return lottos;
    }

    private MainNumbersContainer getWinningNumbers() {
        String input = inputView.winningNumbersInputGuide();
        List<LottoNumber> winningNumbers = InputParser.parseWinningNumbers(input);
        return new MainNumbersContainer(winningNumbers);
    }

    private DrawNumbers getBonusNumberAndBuildDrawNumbers(MainNumbersContainer winningNumbers) {
        String input = inputView.bonusNumberInputGuide();
        LottoNumber bonusNumber = InputParser.parseBonusNumber(input);
        return new DrawNumbers(winningNumbers, bonusNumber);
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
        int attempt = 0;
        while (true) {
            try {
                return RetryExecutor.run(task, attempt++);
            } catch(IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
