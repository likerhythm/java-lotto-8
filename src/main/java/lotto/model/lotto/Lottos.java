package lotto.model.lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.ErrorMessage;
import lotto.dto.LottoResult;
import lotto.exception.LottoPurchaseException;
import lotto.model.numbers.DrawNumbers;
import lotto.model.numbers.LottoNumber;
import lotto.model.numbers.MainNumbersContainer;

public class Lottos {

    public final int LIMIT_QUANTITY = 1000;

    private List<Lotto> lottos;

    public Lottos(int paymentPrice) {
        int count = LottoUtil.calculateLottoCount(paymentPrice);
        validate(count);

        lottos = new ArrayList<>();
        while (count > 0) {
            Lotto lotto = makeLotto();
            boolean duplicated = lottos.stream().anyMatch(r -> r.equals(lotto));
            if (!duplicated) {
                count--;
                lottos.add(lotto);
            }
        }
    }

    public List<String> getLottoInfo() {
        return lottos.stream().map(Lotto::toString).toList();
    }

    public LottoResult check(DrawNumbers drawNumbers) {
        Map<LottoRank, Integer> ranks = checkLottoRank(drawNumbers);
        double rateOfReturn = calculateRateOfReturn(ranks);
        return new LottoResult(ranks, rateOfReturn);
    }

    private Lotto makeLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        List<LottoNumber> lottoNumbers = numbers.stream().map(LottoNumber::of).toList();
        MainNumbersContainer lottoNumbersContainer = new MainNumbersContainer(lottoNumbers);
        return new Lotto(lottoNumbersContainer);
    }

    private double calculateRateOfReturn(Map<LottoRank, Integer> ranks) {
        long totalReward = 0;
        for (Map.Entry<LottoRank, Integer> entry : ranks.entrySet()) {
            long reward = entry.getKey().getReward();
            int count = entry.getValue();
            totalReward += reward * count;
        }
        double v = LottoUtil.calculateRateOfReturn(totalReward, lottos.size());
        return Math.round(v * 100) / 100.0;
    }

    private Map<LottoRank, Integer> checkLottoRank(DrawNumbers drawNumbers) {
        Map<LottoRank, Integer> ranks = LottoRank.makeWinnerLayout();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countWinningNumbers(drawNumbers);
            boolean bonusMatch = lotto.containBonusNumber(drawNumbers);
            LottoRank rank = LottoRank.by(matchCount, bonusMatch);
            ranks.put(rank, ranks.getOrDefault(rank, 0) + 1);
        }
        return ranks;
    }

    private void validate(int count) {
        if (count > LIMIT_QUANTITY) {
            throw new LottoPurchaseException(ErrorMessage.EXCEED_MAX_LOTTO_COUNT.getMessage());
        }
    }
}
