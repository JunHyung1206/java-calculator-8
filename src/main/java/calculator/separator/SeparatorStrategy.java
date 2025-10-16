package calculator.separator;

import java.util.List;

public interface SeparatorStrategy {
    boolean supports(String expression);  // 이 전략이 처리할 수 있을지     // 이 전략이 처리할 수 있는지
    List<Integer> extractOperands(String expression);
}