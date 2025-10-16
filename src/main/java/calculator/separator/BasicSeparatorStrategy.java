package calculator.separator;

import java.util.List;
import java.util.regex.Pattern;

import static calculator.separator.SeparatorConfig.DEFAULT_SEPARATORS;

public class BasicSeparatorStrategy implements SeparatorStrategy {


    @Override
    public boolean supports(String expression) {
        // 숫자로 시작하거나 공백일 때 기본 전략이 처리
        return expression != null && (Pattern.compile("^\\d").matcher(expression).find() || expression.isEmpty());
    }

    @Override
    public List<Integer> extractOperands(String expression) {
        Pattern sepPattern = Pattern.compile("[" + String.join("", DEFAULT_SEPARATORS) + "]");
        return Tokenizer.tokenize(sepPattern, expression);
    }
}