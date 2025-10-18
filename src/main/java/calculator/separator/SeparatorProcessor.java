package calculator.separator;

import java.util.List;

public class SeparatorProcessor {
    private final SeparatorSelector selector;

    public SeparatorProcessor() {
        this.selector = new SeparatorSelector();
    }

    public List<Integer> extractNumbers(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("null 입력은 허용되지 않습니다.");
        }
        return selector.extract(expression);
    }
}
