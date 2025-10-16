package calculator.separator;

import java.util.List;
import java.util.regex.Pattern;

public class BasicSeparatorStrategy implements SeparatorStrategy {

    private static final String[] DEFAULT_SEPARATOR = {",", ":"};

    @Override
    public boolean supports(String expression) {
        // 숫자로 시작하거나 공백일 때 기본 전략이 처리
        return expression != null && (Pattern.compile("^\\d").matcher(expression).find() || expression.isEmpty());
    }

    @Override
    public List<Integer> extractOperands(String expression) {
        Pattern sepPattern = Pattern.compile("[" + String.join("", DEFAULT_SEPARATOR) + "]");
        return Tokenizer.tokenize(sepPattern, expression);
    }
}