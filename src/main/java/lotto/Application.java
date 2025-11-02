package lotto;

import lotto.model.lotto.Lottos;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        LottoAppController lottoAppController = new LottoAppController(new InputView(), new OutputView());
        lottoAppController.run();
    }
}
