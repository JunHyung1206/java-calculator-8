package calculator.separator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomSeparatorStrategy implements SeparatorStrategy{
    private static final Pattern HEADER = Pattern.compile("^" + Pattern.quote("//") + "(.)" + Pattern.quote("\\n"));
    private static final String[] DEFAULT_SEPARATOR = {",", ":"};

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

        String sep = Pattern.quote(matcher.group(1));
        String body = expression.substring(matcher.end());
        Pattern sepPattern = Pattern.compile("[" + String.join("", DEFAULT_SEPARATOR) + sep + "]");


        return Tokenizer.tokenize(sepPattern, body);
    }
}
