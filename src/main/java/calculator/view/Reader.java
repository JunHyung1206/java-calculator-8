package calculator.view;

import calculator.validator.Validator;
import camp.nextstep.edu.missionutils.Console;

public class Reader {
    private static Reader instance;
    private Reader(){
    }

    public static Reader getInstance() {
        if(instance == null){
            instance = new Reader();
        }
        return instance;
    }

    public static String read(){
        String expression = Console.readLine();
        Console.close();
        return expression;
    }
}
