package calculator.separator;

import java.util.List;

public final class SeparatorSelector {
    // 기본 제공 전략(순서 중요: 커스텀 → 기본)
    private final List<SeparatorStrategy> strategies = List.of(new CustomSeparatorStrategy(), new BasicSeparatorStrategy());
    public List<Integer> extract(String expression) {
        for (SeparatorStrategy s : strategies) {
            if (s.supports(expression)) {
                return s.extractOperands(expression);
            }
        }
        throw new IllegalArgumentException("지원하지 않는 입력 형식입니다: " + expression);
    }
}