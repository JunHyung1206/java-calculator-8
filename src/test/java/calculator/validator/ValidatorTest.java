package calculator.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    @DisplayName("아무것도 없는 수식")
    void validationBlank(){
        assertThat(Validator.validate("")).isTrue();
    }

    @Test
    @DisplayName("커스텀 연산자만 있는 수식")
    void validationOnlyCustomSeparator(){
        assertThat(Validator.validate("//,\\n")).isTrue();
    }


    @Test
    @DisplayName("숫자만 있는 수식")
    void validationOnlyNumber(){
        assertThat(Validator.validate("1")).isTrue();
        assertThat(Validator.validate("1234")).isTrue();
        assertThat(Validator.validate("12345")).isTrue();

    }

    @Test
    @DisplayName("기본 구분자만 있는 수식")
    void validationNumberWithDefault(){
        assertThat(Validator.validate("1,2")).isTrue();
        assertThat(Validator.validate("1,2,423")).isTrue();
        assertThat(Validator.validate("1,24312,1234")).isTrue();
        assertThat(Validator.validate("1,24321;5123431243124")).isTrue(); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
    }

    @Test
    @DisplayName("커스텀 구분자도 있는 수식")
    void validationNumberWithCustom(){
        assertThat(Validator.validate("//,\\n1,2")).isTrue();
        assertThat(Validator.validate("//,\\n1,2,423")).isTrue();
        assertThat(Validator.validate("//,\\n1,24312,1234")).isTrue();
        assertThat(Validator.validate("//,\\n1,24321;5123431243124")).isTrue(); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
    }

    @Test
    @DisplayName("실패 케이스 : 커스텀 구분자 표시가 잘못된 경우")
    void missingCustomSeparator(){
        assertThat(Validator.validate("/;\\n11#")).isFalse();
    }

    @Test
    @DisplayName("실패 케이스 : 공백이 있는 경우")
    void existBlank(){
        assertThat(Validator.validate("//;\\n11 2")).isFalse();
        assertThat(Validator.validate("// \\n11,2")).isFalse();
    }

    @Test
    @DisplayName("실패 케이스 : 구분자에 - 경우")
    void existMinus(){
        assertThat(Validator.validate("//-\\n11 2")).isFalse();
        assertThat(Validator.validate("//-\\n11-2")).isFalse();
    }


    @Test
    @DisplayName("실패 케이스 : 구분자에 숫자와 문자를 넣은 경우")
    void separatorInAlphabetOrDigit(){
        assertThat(Validator.validate("//A\\n11A2")).isFalse();
        assertThat(Validator.validate("//0\\n1102")).isFalse();
    }


}