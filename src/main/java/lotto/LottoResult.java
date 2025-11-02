package lotto;

import java.util.Map;

public record LottoResult(
        Map<LottoRank, Integer> ranks,
        double rateOfReturn
) {
}
