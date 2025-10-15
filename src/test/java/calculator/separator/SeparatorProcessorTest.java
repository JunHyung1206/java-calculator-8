package calculator.separator;

import calculator.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SeparatorProcessorTest {
    @Test()
    @DisplayName("커스텀 구분자 부분을 추출한다")
    void extractCustomSeparatorSectionTest() {
        SeparatorProcessor separatorProcessor = new SeparatorProcessor();

        String expression = "//;\\n1;2;3";
        String result = separatorProcessor.extractCustomSeparatorSection(expression);

        assertThat(result).isEqualTo("//;\\n");
    }

    @Test
    @DisplayName("지정된 구분자를 통해 숫자만 추출한다.")
    void NoCustomSeparationTest(){
        SeparatorProcessor separatorProcessor = new SeparatorProcessor();
        List<Integer> operands = separatorProcessor.extractOperand("1,2:3", List.of(":",",",";"));

        List<Integer> expect = List.of(1, 2, 3);
        assertThat(operands).isEqualTo(expect);

        assertThrows(NumberFormatException.class, () -> separatorProcessor.extractOperand("1,2;3:4,5", List.of(":",",")));
    }
}