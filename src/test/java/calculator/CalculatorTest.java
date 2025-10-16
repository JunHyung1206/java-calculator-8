package calculator;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculatorTest {

    StringSumCalculator calculator;
    @BeforeEach
    void setUp() {
        calculator = new StringSumCalculator();
    }

    @Test
    @DisplayName("성공 케이스 : 기본 구분자만 있는 경우")
    void successCaseNoCustomSeparation(){

        assertThat(calculator.calculate("2:2")).isEqualTo(4);
        assertThat(calculator.calculate("1,2:3:4")).isEqualTo(10);
        assertThat(calculator.calculate("1")).isEqualTo(1);
    }

    @Test
    @DisplayName("성공 케이스 : 커스텀 구분자도 같이 존재하는 경우")
    void successCaseWithCustomSeparation(){
        assertThat(calculator.calculate("//;\\n2;2")).isEqualTo(4);
        assertThat(calculator.calculate("//,\\n1,2:3:4,5,6")).isEqualTo(21);
    }

    @Test
    @DisplayName("성공 케이스 : 입력이 없는 경우 0이 반환된다.")
    void notInput(){
        assertThat(calculator.calculate("//;\\n")).isEqualTo(0);
        assertThat(calculator.calculate("")).isEqualTo(0);
    }


    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    @AfterEach
    void tearDown() {
        // 테스트 끝나면 항상 복구
        System.setIn(originalIn);
        System.setOut(originalOut);
        Console.close(); // 캐시된 스캐너 정리
    }

    @Test
    @DisplayName("콘솔에 직접 입력하는 경우")
    void processCalculate_readsFromStdin_andPrintsToStdout() {
        // 1) 입력 준비: 사용자가 콘솔에 타이핑한다고 가정
        // 예: 커스텀 구분자를 쓰는 한 줄 입력
        String fakeUserInput = "//;\\n2,2\n"; // 마지막 \n은 엔터
        System.setIn(new ByteArrayInputStream(fakeUserInput.getBytes()));
        Console.close();

        // 2) Console 싱글턴 리셋
        //    (setIn 한 뒤에 close 해두면 다음 readLine 때 새 System.in을 사용)
        Console.close();

        // 3) 출력 캡처
        ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outBuf));

        // 4) 실행
        StringSumCalculator c = new StringSumCalculator();
        int result = c.processCalculate();

        // 5) 검증
        assertThat(result).isEqualTo(4);

        // Printer.print(result)로 나간 콘솔 출력도 검증 가능
        String stdout = outBuf.toString().trim();
        assertThat(stdout).contains("결과 : 4");
    }

    @Test
    @DisplayName("실패 케이스, Validator가 잘 동작하는지 검증")
    void processCalculateFailTest() {
        // 1) 입력 준비: 사용자가 콘솔에 타이핑한다고 가정
        // 예: 커스텀 구분자를 쓰는 한 줄 입력
        String fakeUserInput = "//;\\n2-2\n"; // 마지막 \n은 엔터
        System.setIn(new ByteArrayInputStream(fakeUserInput.getBytes()));
        Console.close();

        // 2) Console 싱글턴 리셋
        //    (setIn 한 뒤에 close 해두면 다음 readLine 때 새 System.in을 사용)
        Console.close();

        // 3) 출력 캡처
        ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outBuf));

        // 4) 검증
        StringSumCalculator c = new StringSumCalculator();
        assertThrows(IllegalArgumentException.class, () -> c.processCalculate());
    }

}
