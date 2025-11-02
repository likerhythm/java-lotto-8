package lotto.service;

import java.util.List;
import java.util.Map;
import lotto.model.lotto.LottoRank;
import lotto.dto.LottoResult;
import lotto.model.numbers.DrawNumbers;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.LottoUtil;

public class LottoVerifyService {

    private DrawNumbers drawNumbers;
    private List<Lotto> lottos;

    public LottoVerifyService(DrawNumbers drawNumbers, List<Lotto> lottos) {
        this.drawNumbers = drawNumbers;
        this.lottos = lottos;
    }

    public LottoResult check() {
        Map<LottoRank, Integer> ranks = checkLottoRank(this.lottos);
        double rateOfReturn = calculateRateOfReturn(ranks);
        return new LottoResult(ranks, rateOfReturn);
    }

    private double calculateRateOfReturn(Map<LottoRank, Integer> ranks) { // todo 수익률 계산 잘 안됨
        long totalReward = 0;
        for (Map.Entry<LottoRank, Integer> entry : ranks.entrySet()) {
            long reward = entry.getKey().getReward();
            int count = entry.getValue();
            totalReward += reward * count;
        }
        double v = LottoUtil.calculateRateOfReturn(totalReward, lottos.size());
        return Math.round(v * 100) / 100.0;
    }

    private Map<LottoRank, Integer> checkLottoRank(List<Lotto> lottos) {
        Map<LottoRank, Integer> ranks = LottoRank.makeWinnerLayout();
        for (Lotto lotto : lottos) {
            int matchCount = lotto.countWinningNumbers(this.drawNumbers);
            boolean bonusMatch = lotto.containBonusNumber(this.drawNumbers);
            LottoRank rank = LottoRank.by(matchCount, bonusMatch);
            ranks.put(rank, ranks.getOrDefault(rank, 0) + 1);
        }
        return ranks;
    }
}
