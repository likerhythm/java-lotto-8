package lotto.dto;

import java.util.Map;
import lotto.model.lotto.LottoRank;

public record LottoResult(
        Map<LottoRank, Integer> ranks,
        double rateOfReturn
) {
}
