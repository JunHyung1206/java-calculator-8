package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculatorTest {

    Calculator calculator;
    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("성공 케이스 : 기본 구분자만 있는 경우")
    void successCaseNoCustomSeparation(){
        String expression;
        int result;

        expression = "2:2";
        result = calculator.processCalculate(expression);
        assertThat(result).isEqualTo(4);

        expression = "1,2:3:4";
        result = calculator.processCalculate(expression);
        assertThat(result).isEqualTo(10);

        expression = "1";
        result = calculator.processCalculate(expression);
        assertThat(result).isEqualTo(1);

    }

    @Test
    @DisplayName("성공 케이스 : 커스텀 구분자도 같이 존재하는 경우")
    void successCaseWithCustomSeparation(){
        String expression;
        int result;

        expression = "//;\\n2;2";
        result = calculator.processCalculate(expression);
        assertThat(result).isEqualTo(4);

        expression = "//t\\n1,2:3:4t5t6";
        result = calculator.processCalculate(expression);
        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("성공 케이스 : 입력이 없는 경우 0이 반환된다.")
    void notInput(){
        String expression;
        int result;

        expression = "//;\\n";
        result = calculator.processCalculate(expression);
        assertThat(result).isEqualTo(0);

        expression = "";
        result = calculator.processCalculate(expression);
        assertThat(result).isEqualTo(0);
    }

}
