package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.ErrorMessage;
import org.junit.jupiter.api.Test;

class RetryExecutorTest {

    @Test
    void 정상적으로_작업을_실행하면_결과를_반환한다() {
        String result = RetryExecutor.run(() -> "success", 1);
        assertThat(result).isEqualTo("success");
    }

    @Test
    void 시도_횟수가_최대_재시도_횟수를_초과하면_예외를_발생시킨다() {
        assertThatThrownBy(() -> RetryExecutor.run(() -> "fail", 101))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining(ErrorMessage.EXCEED_MAX_RETRY_ATTEMPT.getMessage());
    }

    @Test
    void 작업_실행_중_예외가_발생하면_해당_예외를_그대로_던진다() {
        assertThatThrownBy(() -> RetryExecutor.run(() -> { throw new IllegalArgumentException("테스트 예외");}, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("테스트 예외");
    }
}
