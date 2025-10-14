package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorTest {
    @Test()
    @DisplayName("커스텀 구분자를 추출할 수 있어야한다.")
    void findSeparationTest() {
        Calculator calculator = new Calculator();
        String expression = "//;\n1;2;3";
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
}
