package calculator.separator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CustomSeparatorStrategyTest {
    CustomSeparatorStrategy customSeparatorStrategy;

    @BeforeEach
    void setUp() {
        customSeparatorStrategy = new CustomSeparatorStrategy();
    }


    @Test
    @DisplayName("커스텀 연산자만 있는 수식")
    void validationOnlyCustomSeparator(){
        assertThat(customSeparatorStrategy.supports("//,\\n")).isTrue();
        assertThat(customSeparatorStrategy.supports("//s\\n")).isTrue();
        Assertions.assertThrows(IllegalArgumentException.class, () -> customSeparatorStrategy.extractOperands("//n\\n1n3n2n1"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> customSeparatorStrategy.extractOperands("//-\\n1-3-2-1"));

    }

    @Test
    @DisplayName("커스텀 구분자도 있는 수식")
    void validationNumberWithCustom(){
        assertThat(customSeparatorStrategy.supports("//,\\n1,2")).isTrue();
        assertThat(customSeparatorStrategy.supports("//,\\n1,2,423")).isTrue();
        assertThat(customSeparatorStrategy.supports("//,\\n1,24312,1234")).isTrue();
        assertThat(customSeparatorStrategy.supports("//,\\n1,24321;5123431243124")).isTrue(); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
    }
}