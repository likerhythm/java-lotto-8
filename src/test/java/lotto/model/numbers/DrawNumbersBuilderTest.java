package lotto.model.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DrawNumbersBuilderTest {

    @Test
    void 빌더가_준비되지_않은_상태에서_빌드하려는_경우_예외가_발생한다() {
        Assertions.assertThrows(IllegalStateException.class, () -> DrawNumbersBuilder.builder().build());
    }
}
