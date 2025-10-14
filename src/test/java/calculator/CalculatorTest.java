package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    @Test()
    @DisplayName("커스텀 구분자를 추출할 수 있어야한다.")
    void findSeparationTest() {
        Calculator calculator = new Calculator();
        String expression = "//;\\n1;2;3";
        String result = calculator.extractCustomSeparator(expression);

        assertThat(result).isEqualTo(";");
    }

    @Test
    @DisplayName("구분자를 통해 숫자만을 반환해야한다.")
    void extractOperandTest(){
        Calculator calculator = new Calculator();
        List<Integer> operands = calculator.extractOperand("1,2;3", ";");

        List<Integer> expect = List.of(1, 2, 3);
        assertThat(operands).isEqualTo(expect);

        List<Integer> operands2 = calculator.extractOperand("1,2;3:4,5", ";");
        List<Integer> expect2 = List.of(1, 2, 3, 4, 5);
        assertThat(operands2).isEqualTo(expect2);

    }


    @Test
    @DisplayName("커스텀 구분자가 없는 경우 기본 구분자만 사용해야한다.")
    void NoCustomSeparationTest(){
        Calculator calculator = new Calculator();
        List<Integer> operands = calculator.extractOperand("1,2:3", null);

        List<Integer> expect = List.of(1, 2, 3);
        assertThat(operands).isEqualTo(expect);

        assertThrows(NumberFormatException.class, () -> calculator.extractOperand("1,2;3:4,5", null));

    }

    @Test
    @DisplayName("음수가 나와서는 안된다. 모든 숫자는 양수여야 한다.")
    void NoMinusTest(){
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.extractOperand("1,2;-3", null));

    }
}
