package lotto;

import java.util.function.Supplier;

public class RetryExecutor {

    private static final int MAX_RETRY = 100;

    public static <T> T runWithRetry(Supplier<T> task) {
        int attempt = 0;
        while (true) {
            try {
                return task.get();
            } catch(IllegalArgumentException e) {
                if (attempt >= MAX_RETRY) {
                    throw new RuntimeException("최대 재시도 횟수를 초과했습니다. 처음부터 다시 시도해주세요.");
                }
                System.out.println(e.getMessage());
                attempt++;
            }
        }
    }
}
