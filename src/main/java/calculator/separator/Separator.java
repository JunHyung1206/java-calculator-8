package calculator.separator;

import java.util.List;
import java.util.stream.Stream;

public interface Separator {
    public final static List<String> basicSeparators = List.of(":",",");
    public final static  String startSep = "//";
    public final static  String endSep = "\\n";
    public List<Integer> split(String expression);
}
