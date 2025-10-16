package calculator.separator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultSeparatorStrategyTest {
    BasicSeparatorStrategy separatorStrategy;

    @BeforeEach
    void setUp() {
        separatorStrategy = new BasicSeparatorStrategy();
    }

    @Test
    @DisplayName("아무것도 없는 수식")
    void validationBlank(){
        assertThat(separatorStrategy.supports("")).isTrue();
    }

    @Test
    @DisplayName("숫자만 있는 수식")
    void validationOnlyNumber(){
        assertThat(separatorStrategy.supports("1")).isTrue();
        assertThat(separatorStrategy.supports("1234")).isTrue();
        assertThat(separatorStrategy.supports("12345")).isTrue();

    }

    @Test
    @DisplayName("기본 구분자만 있는 수식")
    void validationNumberWithDefault(){
        assertThat(separatorStrategy.supports("1,2")).isTrue();
        assertThat(separatorStrategy.supports("1,2,423")).isTrue();
        assertThat(separatorStrategy.supports("1,24312,1234")).isTrue();
        assertThat(separatorStrategy.supports("1,24321;5123431243124")).isTrue(); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
    }

    @Test
    @DisplayName("실패 케이스 : 커스텀 구분자 표시가 잘못된 경우")
    void missingCustomSeparator(){
        assertThat(separatorStrategy.supports("/;\\n11#")).isFalse();
    }

    @Test
    @DisplayName("실패 케이스 : 공백이 있는 경우")
    void existBlank(){
        assertThat(separatorStrategy.supports("//;\\n11 2")).isFalse();
        assertThat(separatorStrategy.supports("// \\n11,2")).isFalse();
    }

    @Test
    @DisplayName("실패 케이스 : 구분자에 - 경우")
    void existMinus(){
        assertThat(separatorStrategy.supports("//-\\n11,2")).isFalse();
        assertThat(separatorStrategy.supports("//-\\n11-2")).isFalse();
    }

    @Test
    @DisplayName("실패 케이스 : 커스텀 구분자가 두개 있는 경우")
    void validationNumberWithDoubleCustom(){
        assertThat(separatorStrategy.supports("//,\\n//^\\n1,2")).isFalse();
    }

    @Test
    @DisplayName("실패 케이스 : 구분자에 숫자와 문자를 넣은 경우")
    void separatorInAlphabetOrDigit(){
        assertThat(separatorStrategy.supports("//A\\n11A2")).isFalse();
        assertThat(separatorStrategy.supports("//0\\n1102")).isFalse();
    }


}