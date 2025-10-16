package calculator.separator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Tokenizer {
    private Tokenizer() {}

    static List<Integer> tokenize(Pattern split, String exp) {
        List<Integer> nums = new ArrayList<>();
        if(exp.isBlank()){
            return nums;
        }
        for (String tok : split.split(exp, -1)) { // -1: 빈 토큰 보존
            if (tok.isEmpty()) {
                throw new IllegalArgumentException("빈 피연산자가 포함되어 있습니다.");
            }
            // 피연산자가 숫자가 아닌 경우
            if (!tok.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException("숫자만 허용됩니다: '" + tok + "'");
            }
            try {
                nums.add(Integer.parseInt(tok));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("입력된 숫자가 너무 큽니다.");
            }
        }
        return nums;
    }
}