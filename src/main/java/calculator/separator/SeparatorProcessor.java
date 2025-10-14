package calculator.separator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeparatorProcessor {
    public List<Integer> processingSeparator(String expression) {
        String customSeparator = extractCustomSeparator(expression);

        if (customSeparator != null) {
            expression = expression.replace(CustomSeparatorMarker.startSep + customSeparator + CustomSeparatorMarker.endSep, "");
        }

        return extractOperand(expression, customSeparator);
    }

    private String separatePostProcessing(String preProcessedExpression){
        return preProcessedExpression.substring(CustomSeparatorMarker.startSep.length(), preProcessedExpression.length() - CustomSeparatorMarker.endSep.length());
    }

    public String extractCustomSeparator(String expression){
        String separation = null;
        Pattern separatorPattern = Pattern.compile(Pattern.quote(CustomSeparatorMarker.startSep)  + "." + Pattern.quote(CustomSeparatorMarker.endSep));

        Matcher matcher = separatorPattern.matcher(expression);
        if (matcher.find()) {
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
}
