package calculator.separator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomSeparatorTest {
    CustomSeparator separator;
    @BeforeEach()
    void setUp() {
        separator = new CustomSeparator();
    }


    @Test()
    @DisplayName("커스텀 구분자 부분을 추출한다")
    void extractCustomSeparatorSectionTest() {
        assertThat(separator.extractCustomSeparatorSection("//;\\n1;2;3")).isEqualTo("//;\\n");
    }

    @Test
    @DisplayName("커스텀 구분자 부분 추출 잘 되는지 테스트")
    void extractOperandTest(){
        String expression = "//;\\n1;2;3";
        String section = separator.extractCustomSeparatorSection(expression);
        separator.addSeparations(section);

        assertThat(separator.extractOperand("1:2,3")).isEqualTo(List.of(1, 2, 3));
        assertThat(separator.extractOperand("1:2,3;4")).isEqualTo(List.of(1, 2, 3, 4));
    }

    @Test
    @DisplayName("분할이 잘되는지 확인한다.")
    void splitTest(){
        assertThat(separator.split("//;\\n")).isEqualTo(List.of());
        assertThat(separator.split("//;\\n1")).isEqualTo(List.of(1));
        assertThat(separator.split("//;\\n1,2,3")).isEqualTo(List.of(1,2,3));
        assertThat(separator.split("//;\\n1,2,3,5")).isEqualTo(List.of(1,2,3,5));
    }

    @Test
    @DisplayName("커스텀 구분자 이외의 구분자가 오는 경우 예외가 발생한다.")
    void splitExceptionTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> separator.split("//;\\n3=5=3"));
    }
}