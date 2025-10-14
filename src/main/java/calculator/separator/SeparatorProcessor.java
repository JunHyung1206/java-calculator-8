package calculator.separator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeparatorProcessor {
    List<String> separators = new ArrayList<>();

    // TODO: 기본 구분자, 커스텀 구분자 어떻게 처리하면 좋을지에 대해 고민한다.
    public SeparatorProcessor() {
        separators.add(",");
        separators.add(":");
    }

    public List<Integer> processingSeparator(String expression) {
        // 1. 커스텀 연산자가 있는 부분을 추출합니다.
        String customSeparatorSection = extractCustomSeparatorSection(expression);

        if (customSeparatorSection != null) {
            // 2. 커스텀 연산자가 있다면 해당 수식에서 커스텀 연산자가 있는 부분을 제거합니다.
            expression = removeCustomSeparatorSection(expression, customSeparatorSection);

            // 3. 마커를 제거한 후 해당 커스텀 연산자를 연산자 목록에 추가합니다.
            separators.add(removeMarker(customSeparatorSection));
        }
        // 4. 합할 피연산자만 추출하여 반환합니다.
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

    private String removeMarker(String customSeparatorSection){
        return customSeparatorSection.replace(CustomSeparatorMarker.startSep, "").replace(CustomSeparatorMarker.endSep, "");
    }

    public List<Integer> extractOperand(String expression, List<String> separators){
        List<Integer> operands = new ArrayList<>();
        for (String separator : separators) {
            expression = expression.replace(separator, " ");
        }
        for (String operand : expression.split(" ")) {
            operands.add(Integer.parseInt(operand));
        }
        return operands;
    }
}
