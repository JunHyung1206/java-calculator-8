package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class Reader {
    public String read(){
        String expression = Console.readLine();
        Console.close();

        return expression;
    }
}
