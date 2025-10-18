package calculator.separator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Tokenizer {
    private Tokenizer() {}

    static List<Integer> tokenize(List<String> separators, String exp) {

        // 음수 처리를 위한 전처리

        if (separators.contains("-")) {
            exp = exp.replaceAll("--", SeparatorConfig.DEFAULT_SEPARATORS.getFirst() + "-");
            separators.remove("-");
        }

        Pattern sepPattern = Pattern.compile("[" + String.join("", separators) + "]");
        String[] splitResult = sepPattern.split(exp, -1);

        List<Integer> nums = new ArrayList<>();
        if(exp.isBlank()){
            return nums;
        }
        for (String tok : splitResult) { // -1: 빈 토큰 보존
            if (tok.isEmpty()) {
                throw new IllegalArgumentException("빈 피연산자가 포함되어 있습니다.");
            }
            try {
                if (isContainsMinus(tok)){
                    splitMinusToken(tok, nums);
                } else {
                    nums.add(Integer.parseInt(tok));
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("입력된 토큰을 숫자로 변경할 수 없습니다. : " + tok);
            }
        }
        return nums;
    }

    private static void splitMinusToken(String tok, List<Integer> nums) {
        List<String> splitToken = new ArrayList<>(Arrays.stream(tok.split("-")).filter(s -> !s.isBlank()).toList());

        if (tok.startsWith("-")) {
            splitToken.set(0, "-" + splitToken.getFirst());
        }
        for (String s : splitToken) {
            nums.add(Integer.parseInt(s));
        }
    }

    private static boolean isContainsMinus(String tok) {
        return tok.matches("-?([0-9]+)-[0-9]+");
    }
}