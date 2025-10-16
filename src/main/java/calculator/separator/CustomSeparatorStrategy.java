package calculator.separator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static calculator.separator.SeparatorConfig.DEFAULT_SEPARATORS;

public class CustomSeparatorStrategy implements SeparatorStrategy{
    private static final String START_SEP = "//";
    private static final String END_SEP = "\\n";
    private static final Pattern HEADER = Pattern.compile("^" + Pattern.quote(START_SEP) + "(.)" + Pattern.quote(END_SEP));

    @Override
    public boolean supports(String expression) {
        return expression != null && HEADER.matcher(expression).find();
    }

    @Override
    public List<Integer> extractOperands(String expression) {
        Matcher matcher = HEADER.matcher(expression);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

        String sep = matcher.group(1);
        if (sep.matches("[\\w-]")){
            throw new IllegalArgumentException("구분자는 영어와 숫자, _, -를 제외합니다: " + expression);
        }
        String body = expression.substring(matcher.end());
        Pattern sepPattern = Pattern.compile("[" + String.join("", DEFAULT_SEPARATORS) + sep + "]");


        return Tokenizer.tokenize(sepPattern, body);
    }
}
