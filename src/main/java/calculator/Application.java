package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        String inputExpression = Console.readLine();
        StringSumCalculator calculator = new StringSumCalculator();

        int result = calculator.processCalculate(inputExpression);

        System.out.println("결과 : " + result);
        Console.close();
    }
}