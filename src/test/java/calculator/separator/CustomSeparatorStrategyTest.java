package calculator.separator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThat(customSeparatorStrategy.extractOperands("//,\\n")).isEqualTo(List.of());
    }

    @Test
    @DisplayName("커스텀 구분자가 있는 수식")
    void validationNumberWithCustom(){
        assertThat(customSeparatorStrategy.supports("//,\\n1,2")).isTrue();
        assertThat(customSeparatorStrategy.extractOperands("//,\\n1,2")).isEqualTo(List.of(1, 2));

        assertThat(customSeparatorStrategy.supports("//,\\n1,2,423")).isTrue();
        assertThat(customSeparatorStrategy.extractOperands("//,\\n1,2,423")).isEqualTo(List.of(1, 2, 423));

        assertThat(customSeparatorStrategy.supports("//,\\n1,24312,1234")).isTrue();
        assertThat(customSeparatorStrategy.extractOperands("//,\\n1,24312,1234")).isEqualTo(List.of(1, 24312, 1234));

        assertThat(customSeparatorStrategy.supports("//;\\n1,24321;512343124")).isTrue(); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
        assertThat(customSeparatorStrategy.extractOperands("//;\\n1,24321;51234312")).isEqualTo(List.of(1, 24321, 51234312)); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.


        assertThat(customSeparatorStrategy.supports("//n\\n1n24321n512343124")).isTrue(); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
        assertThat(customSeparatorStrategy.extractOperands("//n\\n1n24321n512343124")).isEqualTo(List.of(1, 24321, 512343124)); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
    }

    @Test
    @DisplayName("성공 케이스 : 구분자로 공백을 사용한 경우")
    void existBlank(){
        assertThat(customSeparatorStrategy.supports("// \\n1 24321 512343124")).isTrue(); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
        assertThat(customSeparatorStrategy.extractOperands("// \\n1 24321 512343124")).isEqualTo(List.of(1, 24321, 512343124)); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.

    }

    @Test
    @DisplayName("실패 케이스 : 커스텀 구분자 표시가 잘못된 경우")
    void missingCustomSeparator(){
        assertThat(customSeparatorStrategy.supports("/;\\n11#")).isFalse();
    }

    @Test
    @DisplayName("수식에 -가 들어간 경우")
    void existMinus(){
        assertThat(customSeparatorStrategy.supports("//-\\n11,2")).isTrue();
        assertThat(customSeparatorStrategy.extractOperands("//-\\n11,2")).isEqualTo(List.of(11, 2));


        assertThat(customSeparatorStrategy.supports("//-\\n11-2")).isTrue();
        assertThat(customSeparatorStrategy.extractOperands("//-\\n11-2")).isEqualTo(List.of(11, 2));

        assertThat(customSeparatorStrategy.supports("//-\\n11,32,-2")).isTrue();
        assertThat(customSeparatorStrategy.extractOperands("//-\\n11,32,-2")).isEqualTo(List.of(11, 32,-2));

        assertThat(customSeparatorStrategy.supports("//-\\n11,32--2")).isTrue();
        assertThat(customSeparatorStrategy.extractOperands("//-\\n11,32--2")).isEqualTo(List.of(11, 32,-2));


        assertThat(customSeparatorStrategy.supports("//-\\n11,32--2-2")).isTrue();
        assertThat(customSeparatorStrategy.extractOperands("//-\\n11,32--2-2")).isEqualTo(List.of(11, 32,-2,2));


    }

    @Test
    @DisplayName("실패 케이스 : 커스텀 구분자가 두개 있는 경우")
    void validationNumberWithDoubleCustom(){
        assertThat(customSeparatorStrategy.supports("//,\\n//^\\n1,2")).isTrue();
        assertThrows(IllegalArgumentException.class, () -> customSeparatorStrategy.extractOperands("//,\\n//^\\n1,2"));
    }

    @Test
    @DisplayName("실패 케이스 : 구분자에 숫자와 문자를 넣은 경우")
    void separatorInAlphabetOrDigit(){
        assertThat(customSeparatorStrategy.supports("//0\\n1102")).isTrue();
        assertThrows(IllegalArgumentException.class, () -> customSeparatorStrategy.extractOperands("//0\\n1102"));
    }


}