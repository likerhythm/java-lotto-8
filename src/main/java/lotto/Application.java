package lotto;

import lotto.singleton.SingletonContainer;

public class Application {
    public static void main(String[] args) {
        LottoAppController controller = SingletonContainer.getInstance(LottoAppController.class);
        controller.run();
    }
}
