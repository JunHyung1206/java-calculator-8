package calculator.separator.v1;

import java.util.List;

public interface Separator {
    List<String> basicSeparators = List.of(":",",");
    String startSep = "//";
    String endSep = "\\n";
    List<Integer> split(String expression);
}
