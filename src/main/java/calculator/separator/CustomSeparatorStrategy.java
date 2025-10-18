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
        if (sep.matches("[0-9]")){
            throw new IllegalArgumentException("구분자에는 숫자를 제외합니다: " + expression + " 구분자 : " + sep);
        }

        String body = expression.substring(matcher.end());
        if (body.matches("^[0-9]]")) {
            throw new IllegalArgumentException("구분자 오류입니다.: " + expression);
        }


        List<String> totalSeparators = new java.util.ArrayList<>(List.copyOf(DEFAULT_SEPARATORS));
        totalSeparators.add(sep);
        return Tokenizer.tokenize(totalSeparators, body);
    }
}
