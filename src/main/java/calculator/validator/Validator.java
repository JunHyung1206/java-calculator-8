package calculator.validator;

import java.util.List;

public class Validator {
    public static void validate(List<Integer> numbers){
        List<Integer> nonPositive = numbers.stream().filter(n -> n <= 0).toList();
        if (!nonPositive.isEmpty()) {
            throw new IllegalArgumentException("양수만 허용합니다: " + nonPositive);
        }
    }
}
