package calculator.separator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SeparatorProcessorTest {
/*    SeparatorProcessor separatorProcessor;

    @BeforeEach
    void setUp(){
        separatorProcessor = new SeparatorProcessor();
    }

    @Test()
    @DisplayName("커스텀 구분자 부분을 추출한다")
    void extractCustomSeparatorSectionTest() {
        String expression = "//;\\n1;2;3";
        String result = separatorProcessor.extractCustomSeparatorSection(expression);

        assertThat(result).isEqualTo("//;\\n");
    }

    @Test
    @DisplayName("지정된 구분자를 통해 숫자만 추출한다.")
    void separationTest(){
        List<Integer> operands = separatorProcessor.extractOperand("1,2:3", List.of(":",",",";"));

        List<Integer> expect = List.of(1, 2, 3);
        assertThat(operands).isEqualTo(expect);

        assertThrows(NumberFormatException.class, () -> separatorProcessor.extractOperand("1,2;3:4,5", List.of(":",",")));
    }

    @Test
    @DisplayName("빈 문자열이면 빈 리스트를 반환한다.")
    void blankTest(){
        List<Integer> result = separatorProcessor.extractOperand("", List.of(":", ","));

        assertThat(result).isEqualTo(List.of());

    }*/
}