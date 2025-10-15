package calculator.separator;

import java.util.List;

public class CustomSeparator extends Separator {
    public CustomSeparator(List<String> separators) {
        setSeparators(separators);
    }
    public void setSeparators(List<String> separators){
        super.separators = separators;
    }
}
