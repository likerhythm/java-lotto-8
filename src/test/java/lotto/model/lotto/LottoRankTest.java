package lotto.model.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class LottoRankTest {

    @Test
    void _1등을_반환한다() {
        assertThat(LottoRank.by(6, false)).isEqualTo(LottoRank._1ST);
        assertThat(LottoRank.by(6, true)).isEqualTo(LottoRank._1ST);
    }

    @Test
    void _2등을_반환한다() {
        assertThat(LottoRank.by(5, true)).isEqualTo(LottoRank._2ND);
    }

    @Test
    void _3등을_반환한다() {
        assertThat(LottoRank.by(5, false)).isEqualTo(LottoRank._3RD);
    }

    @Test
    void _4등을_반환한다() {
        assertThat(LottoRank.by(4, false)).isEqualTo(LottoRank._4TH);
        assertThat(LottoRank.by(4, true)).isEqualTo(LottoRank._4TH);
    }

    @Test
    void _5등을_반환한다() {
        assertThat(LottoRank.by(3, false)).isEqualTo(LottoRank._5TH);
        assertThat(LottoRank.by(3, true)).isEqualTo(LottoRank._5TH);
    }

    @Test
    void _6등을_반환한다() {
        assertThat(LottoRank.by(2, false)).isEqualTo(LottoRank._6TH);
        assertThat(LottoRank.by(2, true)).isEqualTo(LottoRank._6TH);
    }

    @Test
    void _7등을_반환한다() {
        assertThat(LottoRank.by(1, false)).isEqualTo(LottoRank._7TH);
        assertThat(LottoRank.by(1, true)).isEqualTo(LottoRank._7TH);
    }

    @Test
    void _8등을_반환한다() {
        assertThat(LottoRank.by(0, false)).isEqualTo(LottoRank._8TH);
        assertThat(LottoRank.by(0, true)).isEqualTo(LottoRank._8TH);
    }
}
