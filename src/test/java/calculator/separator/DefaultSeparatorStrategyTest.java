package calculator.separator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        assertThat(separatorStrategy.extractOperands("")).isEqualTo(List.of());

    }

    @Test
    @DisplayName("숫자만 있는 수식")
    void validationOnlyNumber(){
        assertThat(separatorStrategy.supports("1")).isTrue();
        assertThat(separatorStrategy.extractOperands("1")).isEqualTo(List.of(1));


        assertThat(separatorStrategy.supports("1234")).isTrue();
        assertThat(separatorStrategy.extractOperands("1234")).isEqualTo(List.of(1234));

        assertThat(separatorStrategy.supports("12345")).isTrue();
        assertThat(separatorStrategy.extractOperands("12345")).isEqualTo(List.of(12345));

    }

    @Test
    @DisplayName("기본 구분자만 있는 수식")
    void validationNumberWithDefault(){
        assertThat(separatorStrategy.supports("1,2")).isTrue();
        assertThat(separatorStrategy.extractOperands("1,2")).isEqualTo(List.of(1,2));

        assertThat(separatorStrategy.supports("1,2,423")).isTrue();
        assertThat(separatorStrategy.extractOperands("1,2,423")).isEqualTo(List.of(1,2,423));

        assertThat(separatorStrategy.supports("1,24312:1234")).isTrue();
        assertThat(separatorStrategy.extractOperands("1,24312:1234")).isEqualTo(List.of(1,24312,1234));

    }

    @Test
    @DisplayName("실패케이스 : 기본 구분자 이외의 구분자가 들어오는 경우")
    void validationWithCustomSeparator(){
        assertThat(separatorStrategy.supports("1;2")).isTrue();
        Assertions.assertThrows(IllegalArgumentException.class, () -> separatorStrategy.extractOperands("1;2"));

        assertThat(separatorStrategy.supports("1;2;423")).isTrue();
        Assertions.assertThrows(IllegalArgumentException.class, () -> separatorStrategy.extractOperands("1;2;423"));

        assertThat(separatorStrategy.supports("1;24312;1234")).isTrue();
        Assertions.assertThrows(IllegalArgumentException.class, () -> separatorStrategy.extractOperands("1;24312;1234"));

        assertThat(separatorStrategy.supports("1;24321;51234")).isTrue(); // 기본구분자 목록에 없는 경우라도 우선은 입력형식만 봄.
        Assertions.assertThrows(IllegalArgumentException.class, () -> separatorStrategy.extractOperands("1;24321;51234"));

    }


}