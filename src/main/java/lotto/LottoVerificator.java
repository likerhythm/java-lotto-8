package lotto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lotto.draw_numbers.DrawNumbers;

public class LottoVerificator {

    private DrawNumbers drawNumbers;
    private List<Lotto> lottos;

    public LottoVerificator(DrawNumbers drawNumbers, List<Lotto> lottos) {
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
        double v = calculateRateOfReturn(totalReward);
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

    private double calculateRateOfReturn(long totalReward) {
        return (totalReward / (((double) Lotto.PRICE) * this.lottos.size())) * 100;
    }
}
