package calculator.separator;

import calculator.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SeparatorProcessorTest {
    @Test()
    @DisplayName("커스텀 구분자를 추출할 수 있어야한다.")
    void findSeparationTest() {
        SeparatorProcessor separatorProcessor = new SeparatorProcessor();

        String expression = "//;\\n1;2;3";
        String result = separatorProcessor.extractCustomSeparator(expression);

        assertThat(result).isEqualTo(";");
    }


    @Test
    @DisplayName("커스텀 구분자가 없는 경우 기본 구분자만 사용해야한다.")
    void NoCustomSeparationTest(){
        SeparatorProcessor separatorProcessor = new SeparatorProcessor();
        List<Integer> operands = separatorProcessor.extractOperand("1,2:3", null);

        List<Integer> expect = List.of(1, 2, 3);
        assertThat(operands).isEqualTo(expect);

        assertThrows(NumberFormatException.class, () -> separatorProcessor.extractOperand("1,2;3:4,5", null));
    }

    @Test
    @DisplayName("구분자를 통해 숫자만을 반환해야한다.")
    void extractOperandTest(){
        SeparatorProcessor separatorProcessor = new SeparatorProcessor();
        List<Integer> operands = separatorProcessor.extractOperand("1,2;3", ";");

        List<Integer> expect = List.of(1, 2, 3);
        assertThat(operands).isEqualTo(expect);

        List<Integer> operands2 = separatorProcessor.extractOperand("1,2;3:4,5", ";");
        List<Integer> expect2 = List.of(1, 2, 3, 4, 5);
        assertThat(operands2).isEqualTo(expect2);
    }
}