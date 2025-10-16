package calculator;

import calculator.separator.SeparatorProcessor;
import calculator.validator.Validator;
import calculator.view.Reader;
import calculator.view.Printer;

import java.util.List;

// 문자열 덧셈 계산기
public class StringSumCalculator {
    private final SeparatorProcessor processor = new SeparatorProcessor();

    public int calculate(String expression) {
        List<Integer> numbers = processor.extractNumbers(expression);
        Validator.validate(numbers);
        int sum = 0;
        try{
            sum = numbers.stream()
                .reduce(0, Math::addExact);
        } catch (ArithmeticException e){
            throw new IllegalArgumentException("계산 과정 중 오버플로우가 발생하였습니다.");
        }
        return sum;
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