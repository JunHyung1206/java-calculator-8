package calculator.separator;

import java.util.List;
import java.util.stream.Stream;

public abstract class Separator {
    protected List<String> separators;
    public List<String> getSeparators() {
        return separators;
    }

    List<String> getConcatSeparators(Separator separator) {
        return Stream.concat(this.getSeparators().stream(), separator.getSeparators().stream()).toList();
    }
}
