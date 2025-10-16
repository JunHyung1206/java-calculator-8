package calculator.separator;

import java.util.ArrayList;
import java.util.List;

public class BasicSeparator implements Separator {
    public List<Integer> extractOperand(String expression){
        List<Integer> operands = new ArrayList<>();
        String splitPattern = "[" + String.join("", basicSeparators) + "]";

        for (String s : expression.split(splitPattern)) {
            operands.add(Integer.parseInt(s));
        }
        return operands;
    }

    @Override
    public List<Integer> split(String expression) {
        if (expression.isBlank()){
            return new ArrayList<>();
        }
        return extractOperand(expression);
    }
}
