package lotto.model.lotto;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import lotto.util.StringParser;

public enum LottoRank {

    _1ST(2_000_000_000, 6, null),
    _2ND(30_000_000, 5, Boolean.TRUE),
    _3RD(1_500_000, 5, Boolean.FALSE),
    _4TH(50_000, 4, null),
    _5TH(5_000, 3, null),
    _6TH(0, 2, null),
    _7TH(0, 1, null),
    _8TH(0, 0, null),
    ;

    private final int reward;
    private final Integer matchCount;
    private final Boolean bonusMatch;

    LottoRank(int reward, Integer matchCount, Boolean bonusMatch) {
        this.reward = reward;
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
    }

    public static LottoRank by (int matchCount, boolean bonusMatch) {
        return Arrays.stream(LottoRank.values())
                .filter(lr -> lr.check(matchCount, bonusMatch))
                .findFirst()
                .orElse(_8TH);
    }

    public static Map<LottoRank, Integer> makeWinnerLayout() {
        Map<LottoRank, Integer> ranks = new LinkedHashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            if (rank.isWinner()) {
                ranks.put(rank, 0);
            }
        }
        return ranks;
    }

    public int getReward() {
        return this.reward;
    }

    public boolean isWinner() {
        return this.reward > 0;
    }

    @Override
    public String toString() {
        String bonusText = (this.bonusMatch != null && this.bonusMatch) ? ", 보너스 볼 일치" : "";
        return String.format("%d개 일치%s (%s원)",
                this.matchCount,
                bonusText,
                StringParser.numberFormat(this.reward));
    }

    private boolean check(int matchCount, boolean bonusMatch) {
        if (this.bonusMatch != null) {
            return this.matchCount == matchCount && this.bonusMatch == bonusMatch;
        }
        return this.matchCount == matchCount;
    }
}
