package lotto.util;

import java.util.function.Supplier;

public class RetryExecutor {

    private static final int MAX_RETRY = 100;

    public static <T> T run(Supplier<T> task, int attempt) {
        if (attempt > MAX_RETRY) {
            throw new RuntimeException("[ERROR] 최대 재시도 횟수를 초과했습니다. 처음부터 다시 시도해주세요.");
        }
        return task.get();
    }
}
