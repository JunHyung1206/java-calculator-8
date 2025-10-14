package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculatorTest {
    @Test
    @DisplayName("음수가 나와서는 안된다. 모든 숫자는 양수여야 한다.")
    void NoMinusTest(){
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.isContainNegativeInteger(List.of(-1, 2, 3)));

    }
}
