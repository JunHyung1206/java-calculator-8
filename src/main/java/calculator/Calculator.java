package calculator;

import calculator.separator.CustomSeparatorMarker;
import calculator.separator.SeparatorProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public Calculator() {
        // TODO: 1. 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
        // TODO: 2. 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
        // ex) "//;\n1;2;3" => 구분자로 사용한다.

    }


    public int processLogic(String expression) throws IllegalArgumentException {

        SeparatorProcessor separatorProcessor = new SeparatorProcessor();
        List<Integer> operands = separatorProcessor.processingSeparator(expression);

        // 4. 음수가 있는지 확인한다. 있다면 예외를 던진다.
        isContainNegativeInteger(operands);

        // 5. 숫자를 전부 더한다.
        return calculateAdd(operands);
    }

    public void isContainNegativeInteger(List<Integer> operands) throws IllegalArgumentException {
        for (Integer operand : operands) {
            if (operand < 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    public int calculateAdd(List<Integer> operands){
        int result = 0;
        for (Integer operand : operands) {
            result += operand;
        }
        return result;
    }
}