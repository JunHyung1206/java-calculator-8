package calculator.separator;

import java.util.List;

public final class SeparatorSelector {
    private final List<SeparatorStrategy> strategies;

    public SeparatorSelector(List<SeparatorStrategy> strategies) {
        this.strategies = strategies;
    }

    public List<Integer> extract(String expression) {
        for (SeparatorStrategy s : strategies) {
            if (s.supports(expression)) {
                return s.extractOperands(expression);
            }
        }
        throw new IllegalArgumentException("지원하지 않는 입력 형식입니다: " + expression);
    }
}