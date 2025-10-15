package calculator.separator;

import java.util.ArrayList;
import java.util.List;

public class BasicSeparator implements Separator {
    public List<Integer> extractOperand(String expression){
        List<Integer> operands = new ArrayList<>();
        String splitPattern = "[" + String.join("", basicSeparators) + "]";

        String removedSeparators = expression.replaceAll(splitPattern, " ");
        if(removedSeparators.isBlank()){
            return operands;
        }

        for (String s : removedSeparators.split(" ")) {
            operands.add(Integer.parseInt(s));
        }
        return operands;
    }


    @Override
    public List<Integer> split(String expression) {
        return extractOperand(expression);
    }
}
