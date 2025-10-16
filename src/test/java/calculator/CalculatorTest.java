package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculatorTest {

    StringSumCalculator calculator;
    @BeforeEach
    void setUp() {
        calculator = new StringSumCalculator();
    }

    @Test
    @DisplayName("성공 케이스 : 기본 구분자만 있는 경우")
    void successCaseNoCustomSeparation(){
        String expression;
        int result;

        expression = "2:2";
        result = calculator.calculate(expression);
        assertThat(result).isEqualTo(4);

        expression = "1,2:3:4";
        result = calculator.calculate(expression);
        assertThat(result).isEqualTo(10);

        expression = "1";
        result = calculator.calculate(expression);
        assertThat(result).isEqualTo(1);

    }

    @Test
    @DisplayName("성공 케이스 : 커스텀 구분자도 같이 존재하는 경우")
    void successCaseWithCustomSeparation(){
        String expression;
        int result;

        expression = "//;\\n2;2";
        result = calculator.calculate(expression);
        assertThat(result).isEqualTo(4);

        expression = "//t\\n1,2:3:4t5t6";
        result = calculator.calculate(expression);
        assertThat(result).isEqualTo(21);
    }

    @Test
    @DisplayName("성공 케이스 : 입력이 없는 경우 0이 반환된다.")
    void notInput(){
        String expression;
        int result;

        expression = "//;\\n";
        result = calculator.calculate(expression);
        assertThat(result).isEqualTo(0);

        expression = "";
        result = calculator.calculate(expression);
        assertThat(result).isEqualTo(0);
    }


    // TODO: 이 부분은 다른 밸리데이션 하는 부분을 마련
    @Test
    @DisplayName("실패 케이스 : 커스텀 구분자 이외의 구분자가 입력된 경우")
    void notSeparator(){
        String expression = "//;\\n11#";
        calculator.calculate(expression);
    }

    @Test
    @DisplayName("실패 케이스 : 커스텀 구분자 표시가 잘못된 경우")
    void missingCustomSeparator(){
        String expression = "/;\\n11#";
        calculator.calculate(expression);
    }


    @Test
    @DisplayName("실패 케이스 : 공백이 있는 경우도 잘못된 값으로 간주합니다.")
    void existBlank(){
        String expression = "/;\\n11 2";
        calculator.calculate(expression);

        expression = " ";
        calculator.calculate(expression);
    }


    @Test
    @DisplayName("실패 케이스 : 커스텀 구분자에는 숫자를 허용하지 않는다. 추가적으로 -도 허용하지 않는다")
    void separatorRuleFault(){
        String expression = "/-\\n11";
        calculator.calculate(expression);

        expression = "/0\\n11";
        calculator.calculate(expression);

    }
}
