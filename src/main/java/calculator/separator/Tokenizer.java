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
            // 숫자만 허용(필요 시 규칙 확장 가능)
            if (!tok.chars().allMatch(Character::isDigit)) {
                throw new IllegalArgumentException("숫자만 허용됩니다: '" + tok + "'");
            }
            int v = Integer.parseInt(tok);
            if (v <= 0) {
                throw new IllegalArgumentException("양수만 허용합니다: " + v);
            }
            nums.add(v);
        }
        return nums;
    }
}