package calculator.separator;

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
    void splitTest(){
        assertThat(separator.split("//;\\n")).isEqualTo(List.of());
        assertThat(separator.split("//;\\n1")).isEqualTo(List.of(1));

    }

}