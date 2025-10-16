package calculator.separator.v1;

import java.util.ArrayList;
import java.util.List;

public class BasicSeparator implements Separator {
    public List<Integer> extractOperand(String expression){
        List<Integer> operands = new ArrayList<>();
        String splitPattern = "[" + String.join("", basicSeparators) + "]";

        for (String s : expression.split(splitPattern)) {
            if(!isNumeric(s)){
                throw new IllegalArgumentException("지정된 구분자 이외의 구분자가 사용되었습니다.");
            }
            operands.add(Integer.parseInt(s));
        }
        return operands;
    }

    private boolean isNumeric(String s) {
        return s.matches("\\d+");
    }

    @Override
    public List<Integer> split(String expression) {
        if (expression.isBlank()){
            return new ArrayList<>();
        }
        return extractOperand(expression);
    }
}
