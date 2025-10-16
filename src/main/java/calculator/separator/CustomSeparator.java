package calculator.separator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomSeparator implements Separator {
    private List<String> separators;

    public CustomSeparator() {
        this.separators = new ArrayList<>();
    }

    public static boolean isExistCustomSeparator(String expression) {
        Pattern separatorPattern = Pattern.compile(getCustomRegex());
        return separatorPattern.matcher(expression).find();
    }

    public String extractCustomSeparatorSection(String expression){
        Pattern separatorPattern = Pattern.compile(getCustomRegex());
        Matcher matcher = separatorPattern.matcher(expression);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }

    public static String getCustomRegex() {
        return Pattern.quote(startSep) + "[^\\s\\w-]" + Pattern.quote(endSep);
    }

    public void addSeparations(String customSeparatorSection){
        separators = List.of(customSeparatorSection.replace(startSep, "").replace(endSep, ""));
    }


    public List<Integer> extractOperand(String expression){
        List<Integer> operands = new ArrayList<>();
        String splitPattern = "[" + String.join("", basicSeparators) + String.join("", separators) + "]";

        for (String s : expression.split(splitPattern)) {
            operands.add(Integer.parseInt(s));
        }

        return operands;
    }


    @Override
    public List<Integer> split(String expression) {
        // 1. 커스텀 연산자가 있는 부분을 추출합니다. 만약 커스텀 연산자만 있다면 빈 리스트를 반환합니다.
        // 2. 해당 수식에서 커스텀 연산자가 있는 부분을 제거합니다.
        // 3. 마커를 제거한 후 해당 커스텀 연산자를 연산자 목록에 추가합니다.
        // 4. 연산자 목록에 따라 수식을 분리합니다.

        String customSeparatorSection = extractCustomSeparatorSection(expression);
        if (customSeparatorSection.equals(expression)){
            return new ArrayList<>();
        }
        expression = expression.replace(customSeparatorSection, "");
        addSeparations(customSeparatorSection);

        return extractOperand(expression);
    }
}
