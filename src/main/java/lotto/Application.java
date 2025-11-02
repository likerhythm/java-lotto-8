package lotto;

public class Application {
    public static void main(String[] args) {
        LottoApp lottoApp = new LottoApp(new InputView(), new OutputView());
        lottoApp.run();
    }
}
