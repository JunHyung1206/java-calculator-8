package calculator;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
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
        assertThat(calculator.calculate("2:2")).isEqualTo(4);
        assertThat(calculator.calculate("1,2:3:4")).isEqualTo(10);
        assertThat(calculator.calculate("1")).isEqualTo(1);
    }

    @Test
    @DisplayName("성공 케이스 : 커스텀 구분자도 같이 존재하는 경우")
    void successCaseWithCustomSeparation(){
        assertThat(calculator.calculate("//;\\n2;2")).isEqualTo(4);
        assertThat(calculator.calculate("//,\\n1,2:3:4,5,6")).isEqualTo(21);
    }

    @Test
    @DisplayName("성공 케이스 : 입력이 없는 경우 0이 반환된다.")
    void notInput(){
        assertThat(calculator.calculate("//;\\n")).isEqualTo(0);
        assertThat(calculator.calculate("")).isEqualTo(0);
    }

    @Test
    @DisplayName("실패 케이스 : 양수로 구성된 경우가 아닌 경우")
    void notPositiveNumber(){
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("//;\\n2;2,0"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("//,\\n1,2:3:4,5,6,-1"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("0"));
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("-2"));

    }

    @Test
    @DisplayName("실패 케이스 : 입력 과정 중 오버플로우 발생")
    void inputOverflow() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("210000000000000000000"));

    }

    @Test
    @DisplayName("실패 케이스 : 계산 과정 중 오버플로우 발생")
    void sumOverflow() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate("2100000000,2100000000"));
    }


}
