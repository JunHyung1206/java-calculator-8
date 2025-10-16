# **java-calculator-precourse**

## 과제 요구 사항
### **기능 요구 사항**

입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

- 쉼표(,) 또는 콜론(:)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
    - 예: "" => 0, "1,2" => 3, "1,2,3" => 6, "1,2:3" => 6
- 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다. 커스텀 구분자는 문자열 앞부분의 "//"와 "\n" 사이에 위치하는 문자를 커스텀 구분자로 사용한다.
    - 예를 들어 "//;\n1;2;3"과 같이 값을 입력할 경우 커스텀 구분자는 세미콜론(;)이며, 결과 값은 6이 반환되어야 한다.
- 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.
    
<br/>  

### **입출력 요구 사항**
#### **입력**
- 구분자와 양수로 구성된 문자열

#### **출력**
- 덧셈 결과
```
결과 : 6
```

#### **실행 결과 예시**
```
덧셈할 문자열을 입력해 주세요.
1,2:3
결과 : 6
```
<br/>  


### **프로그래밍 요구 사항**
- JDK 21 버전에서 실행 가능해야 한다.
- 프로그램 실행의 시작점은 `Application`의 `main()`이다.
- `build.gradle` 파일은 변경할 수 없으며, **제공된 라이브러리 이외의 외부 라이브러리는 사용하지 않는다.**
- 프로그램 종료 시 `System.exit()`를 호출하지 않는다.
- 프로그래밍 요구 사항에서 달리 명시하지 않는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
- 자바 코드 컨벤션을 지키면서 프로그래밍한다.
    - 기본적으로 [Java Style Guide](https://github.com/woowacourse/woowacourse-docs/blob/main/styleguide/java)를 원칙으로 한다.

#### **라이브러리**

- `camp.nextstep.edu.missionutils`에서 제공하는 `Console` API를 사용하여 구현해야 한다.
    - 사용자가 입력하는 값은 `camp.nextstep.edu.missionutils.Console`의 `readLine()`을 활용한다.
<br/>  


---
## 구현할 기능 목록
### 기본 기능
- [x]  식을 입력받는다
- [x]  결과값을 출력한다.
- [x]  커스텀 구분자를 찾는다.
- [x]  `, :`와 찾은 커스텀 구분자 기준으로 식을 구분한다.
- [x]  모든 숫자를 합산한다.
- [x]  만약 숫자 중 음수가 있는 경우 `IllegalArgumentException`를 발생시키고 종료한다.

<br/>

### 추가 기능
- [x] 커스텀 구분자 및 기본 구분자를 변경 용이한 구조로 변경한다.
- [x] 입력값이 올바른지 검증한다.
- [x] 입력 과정 혹은 연산 과정 중 오버플로우를 탐지한다.

<br/>


---
## 프로젝트 구조
```angular2html
📦 main
└── 📂 java
    └── 📂 calculator
        ├── 📂 separator
        │   ├── 📄 BasicSeparatorStrategy.java
        │   ├── 📄 CustomSeparatorStrategy.java
        │   ├── 📄 SeparatorConfig.java
        │   ├── 📄 SeparatorProcessor.java
        │   ├── 📄 SeparatorSelector.java
        │   ├── 📄 SeparatorStrategy.java
        │   └── 📄 Tokenizer.java
        │
        ├── 📂 validator
        │   └── 📄 Validator.java
        │
        ├── 📂 view
        │   ├── 📄 Printer.java
        │   └── 📄 Reader.java
        │
        ├── 📄 Application.java
        └── 📄 StringSumCalculator.java
```
<br/>  

### 각 클래스의 역할
| 클래스                    | 역할                                  |
|:-----------------------|:------------------------------------|
| `Reader, Printer`      | 콘솔 입출력 담당                           |
| `StringSumCalculator`  | 입출력, 분리 등의 계산하는 모든 프로세스 담당          |
| `SeparatorProcessor`   | 수식의 분리 프로세스 전반을 담당                  |
| `SeparatorStrategy`    | 문자열을 분리하는 전략                        |
| `SeparatorSelector`    | 어떤 전략을 사용할지 결정                      |
| `Tokenizer`            | 구분자를 통해 실제 토큰을 분리하고 리스트로 변환         |
| `SeparatorConfig`      | 기본 구분자에 대한 정보                       |
| `Validator`            | 비즈니스 상의 규칙이 유효한지 담당                 |

<br/>

### 주요 클래스 다이어그램
![img.png](img.png)

