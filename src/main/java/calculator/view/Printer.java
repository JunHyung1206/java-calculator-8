package calculator.view;

public class Printer {
    private static Printer instance;
    private Printer(){
    }

    public static Printer getInstance() {
        if(instance == null){
            instance = new Printer();
        }
        return instance;
    }

    public static void print(int result){
        System.out.println("결과 : " + result);
    }
}
