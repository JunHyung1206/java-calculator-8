package calculator.separator.v1;

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
            if(!isNumeric(s)){
                throw new IllegalArgumentException("지정된 구분자 이외의 구분자가 사용되었습니다.");
            }
            operands.add(Integer.parseInt(s));
        }
        return operands;
    }

    private boolean isNumeric(String s) {
        return s.matches("\\d+");
    }

    @Override
    public List<Integer> split(String expression) {
        String customSeparatorSection = extractCustomSeparatorSection(expression);
        if (customSeparatorSection.equals(expression)){
            return new ArrayList<>();
        }
        expression = expression.replace(customSeparatorSection, "");
        addSeparations(customSeparatorSection);

        return extractOperand(expression);
    }
}
