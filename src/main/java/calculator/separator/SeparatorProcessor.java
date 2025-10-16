package calculator.separator;

import java.util.List;

public class SeparatorProcessor {
    private Separator separators;

    public SeparatorProcessor() {
        separators = new BasicSeparator();
    }

    public List<Integer> extractOperand(String expression) {
        // 1. 커스텀 연산자 부분이 있는지 확인한다.
        // 2. 있다면 separator를 CustomSeparator로 사용한다. 없다면 separator를 BasicSeparator로 사용한다.
        // 3. 분리해서 반환한다.

        if(CustomSeparator.isExistCustomSeparator(expression)){
            separators = new CustomSeparator();
        }
        return separators.split(expression);
    }
}
