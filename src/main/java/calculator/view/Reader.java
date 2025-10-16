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

        if(!Validator.validate(expression)){
            throw new IllegalArgumentException("입력 형식이 올바르지 않습니다.");
        }
        return expression;
    }
}
