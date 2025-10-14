package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    /*
      프로그래밍 요구 사항
      JDK 21 버전에서 실행 가능해야 한다.
      프로그램 실행의 시작점은 Application의 main()이다.
      build.gradle 파일은 변경할 수 없으며, 제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.
      프로그램 종료 시 System.exit()를 호출하지 않는다.
      프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
      자바 코드 컨벤션을 지키면서 프로그래밍한다.
      기본적으로 Java Style Guide를 원칙으로 한다.
      라이브러리
          camp.nextstep.edu.missionutils에서 제공하는 Console API를 사용하여 구현해야 한다.
          사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용한다.
     */
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        String s = Console.readLine();
        Calculator calculator = new Calculator();

        int result = calculator.processLogic(s);
        System.out.println("결과 : " + result);

        Console.close();

    }
}