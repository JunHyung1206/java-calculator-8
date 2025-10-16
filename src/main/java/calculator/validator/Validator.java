package calculator.validator;

import calculator.separator.CustomSeparator;
import calculator.separator.Separator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validate(String input){

        String matchResult;
        // 원하는 것 : "(//.\\n)*(|\\d+)"
        Pattern validPattern = Pattern.compile("(" + CustomSeparator.getCustomRegex() + ")" + "*" + "(\\d+([^\\s\\w]\\d+)*|)");
        Matcher matcher = validPattern.matcher(input);

        if(matcher.find()){
            matchResult = matcher.group();
            return matchResult.equals(input);
        }

        return false;
    }
}
