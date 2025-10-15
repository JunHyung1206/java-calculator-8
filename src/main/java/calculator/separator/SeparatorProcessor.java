package calculator.separator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeparatorProcessor {
    private List<String> separators;
    private final BasicSeparator basicSeparator;

    public SeparatorProcessor() {
        separators = new ArrayList<>();
        basicSeparator = new BasicSeparator();
        separators.addAll(basicSeparator.getSeparators());
    }

    public List<Integer> processingSeparator(String expression) {
        // 1. 커스텀 연산자가 있는 부분을 추출합니다.
        // 2. 커스텀 연산자가 있다면 해당 수식에서 커스텀 연산자가 있는 부분을 제거합니다.
        // 3. 마커를 제거한 후 해당 커스텀 연산자를 연산자 목록에 추가합니다.
        // 4. 합할 피연산자만 추출하여 반환합니다.

        String customSeparatorSection = extractCustomSeparatorSection(expression);
        if (customSeparatorSection != null) {
            expression = removeCustomSeparatorSection(expression, customSeparatorSection);
            CustomSeparator customSeparator = new CustomSeparator(removeMarker(customSeparatorSection));
            separators = basicSeparator.getConcatSeparators(customSeparator);
        }
        return extractOperand(expression, separators);
    }

    private String removeCustomSeparatorSection(String expression, String customSeparatorSection) {
        return expression.replace(customSeparatorSection, "").strip();
    }

    public String extractCustomSeparatorSection(String expression){
        Pattern separatorPattern = Pattern.compile(Pattern.quote(CustomSeparatorMarker.startSep)  + "." + Pattern.quote(CustomSeparatorMarker.endSep));
        Matcher matcher = separatorPattern.matcher(expression);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    private List<String> removeMarker(String customSeparatorSection){
        return List.of(customSeparatorSection.replace(CustomSeparatorMarker.startSep, "").replace(CustomSeparatorMarker.endSep, ""));
    }

    public List<Integer> extractOperand(String expression, List<String> separators){
        List<Integer> operands = new ArrayList<>();
        for (String separator : separators) {
            expression = expression.replace(separator, " ");
        }
        expression = expression.strip();

        if(expression.isBlank()) {
            return operands;
        }

        for (String operand : expression.split(" ")) {
            operands.add(Integer.parseInt(operand));
        }
        return operands;
    }
}
