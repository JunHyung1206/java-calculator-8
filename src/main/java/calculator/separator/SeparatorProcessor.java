package calculator.separator;

import java.util.List;

public class SeparatorProcessor {
    private final SeparatorSelector selector;

    public SeparatorProcessor() {
        // 기본 제공 전략(순서 중요: 커스텀 → 기본)
        this.selector = new SeparatorSelector(
                List.of(new CustomSeparatorStrategy(), new BasicSeparatorStrategy())
        );
    }

    // 테스트나 확장을 위해 주입 가능하게 하는 보조 생성자
    public SeparatorProcessor(SeparatorSelector selector) {
        this.selector = selector;
    }


    public List<Integer> extractNumbers(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("null 입력은 허용되지 않습니다.");
        }
        return selector.extract(expression);
    }
}
