package lotto.util;

import java.util.function.Supplier;
import lotto.ErrorMessage;

public class RetryExecutor {

    private static final int MAX_RETRY = 100;

    public static <T> T run(Supplier<T> task, int attempt) {
        if (attempt > MAX_RETRY) {
            throw new RuntimeException(ErrorMessage.EXCEED_MAX_RETRY_ATTEMPT.getMessage());
        }
        return task.get();
    }
}
