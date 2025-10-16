package calculator;

import calculator.separator.SeparatorProcessor;
import calculator.view.Reader;
import calculator.view.Printer;

import java.util.List;

// 문자열 덧셈 계산기
public class StringSumCalculator {

    public int calculate(String expression) {
        // 1. 구분자에 대한 내용은 SeparatorProcessor에서 처리한다.
        // 2. 각 피연산자에 대해서 음수가 있다면 예외를 던진다.
        // 3. 각 피연산자에 대한 합을 반환한다.
        List<Integer> operands = new SeparatorProcessor().extractOperand(expression);
        if (operands.stream().anyMatch(i -> i <= 0)) {
            throw new IllegalArgumentException();
        }
        return operands.stream().mapToInt(Integer::intValue).sum();
    }

    public int processCalculate() {
        // 1. 입력을 받는 것은 계산기 안에서 수행 할 일이다.
        // 2. 결과값을 계산한다.
        // 3. 당연히 출력도 계산기 내에서 처리된다.

        String expression = Reader.read();
        int result = calculate(expression);
        Printer.print(result);
        return result;
    }
}