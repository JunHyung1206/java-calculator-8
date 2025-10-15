package calculator.separator;

import java.util.List;

public class BasicSeparator extends Separator {
    static final private List<String> separators = List.of(":",",");

    @Override
    public List<String> getSeparators() {
        return separators;
    }
}
