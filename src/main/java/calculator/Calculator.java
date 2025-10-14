package calculator;

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


    public int processLogic(String expression) {
        // 1. 커스텀 구분자가 있는 경우 커스텀 구분자를 구한다.
        String customSeparator = extractCustomSeparator(expression);

        // 2. 숫자만을 전부 구한다.
        List<Integer> operands = extractOperand(expression, customSeparator);

        // 3. 음수가 있는지 확인한다. 있다면 예외를 던진다.
        operands.stream().filter(i-> i< 0).findFirst().orElseThrow(IllegalArgumentException::new);

        // 4. 숫자를 전부 더한다.
        return calculateAdd(operands);
    }

    private String separatePostProcessing(String preProcessedExpression){
        String startSep = "//";
        String endSep = "\n";
        return preProcessedExpression.substring(startSep.length(), preProcessedExpression.length() - endSep.length());
    }

    public String extractCustomSeparator(String expression){
        String separation = null;
        Pattern separationPattern = Pattern.compile("//.\n");

        Matcher matcher = separationPattern.matcher(expression);

        if(matcher.find()){
            separation = separatePostProcessing(matcher.group());
        }
        return separation;
    }

    public List<Integer> extractOperand(String expression, String customSeparator){
        List<Integer> operands = new ArrayList<>();
        expression = expression.replace(":", " ").replace(",", " ");
        if (customSeparator != null){
            expression = expression.replace(customSeparator, " ");
        }

        for (String s : expression.split(" ")) {
            operands.add(Integer.parseInt(s));
        }
        return operands;
    }

    public int calculateAdd(List<Integer> operands){
        int result = 0;
        for (Integer operand : operands) {
            result += operand;
        }
        return result;
    }
}