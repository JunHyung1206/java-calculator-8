package calculator.validator;

import calculator.separator.CustomSeparator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static boolean validate(String input){
        String matchResult;
        Pattern validPattern = Pattern.compile("(" + CustomSeparator.getCustomRegex() + ")" + "?" + "(\\d+([^\\s\\w]\\d+)*|)");
        Matcher matcher = validPattern.matcher(input);

        if(matcher.find()){
            matchResult = matcher.group();
            return matchResult.equals(input);
        }

        return false;
    }
}
