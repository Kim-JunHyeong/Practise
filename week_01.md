## 1주차 : JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.

---

## 학습 목표

자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기.

## 목차

- JVM이란 무엇인가
- 컴파일 하는 방법
- 실행하는 방법
- 바이트코드란 무엇인가
- JIT 컴파일러란 무엇이며 어떻게 동작하는가
- JVM 구성 요소
- JDK와 JRE의 차이

---

### JVM 이란 무엇인가

> JVM은 실제 운영체제를 대신하여 자바 프로그램을 실행하는 가상의 운영체제 역할을 수행

Java는 WORA(Write Once Run Anywhere)를 목표로 물리적인 머신(ex.운영체제)과는 별개의 가상 머신을 기반으로 자바 프로그램이 동작하도록 설계되었습니다. 이를 위해 운영체제와 자바 프로그램 사이에 JVM(Java Virtual Machine)을 두고 Java를 컴파일 할 때 완전한 기계어로 컴파일하지 않고 중간 단계의 바이트코드를 생성하고, 생성된 바이트코드를 JVM이 해석하고  해당 운영체제에 맞게 기계어로 번역해 줍니다.

이처럼 JVM은 중간자 역할을 하는 바이트코드를 해석하고 실행하는 역할을 하며, JVM이 없었다면 각각의 운영체제에 맞게 프로그램을 다시 만들어야 했습니다. 물론 운영체제에서 바로 실행되지 않고 JVM을 한 번 더 거치기 때문에 컴파일링 과정이 추가적으로 존재하며 속도가 느리다는 단점이 존재했지만, 현재는 기술이 높아짐에 따라 하드웨어의 성능도 좋아지고 바이트코드로 변환해 주는 JVM 내부의 JIT 컴파일러가 최적화됨에 따라 최근에는 속도가 느리다는 단점은 거의 없어졌습니다.

하지만 JVM 자체는 운영체제에 종속적이며 각각의 OS에 맞는 JVM이 설치가 되어야 합니다. JVM은 JVM 명세를 따르기만 하면 어떠한 벤더든 JVM을 개발하여 제공할 수 있습니다.

---

### 컴파일 하는 방법

> JDK bin 하위 폴더에 있는 javac를 사용하여 컴파일

에디터를 이용하여 아래의 코드를 작성하고 CompileAndRun.java 라는 파일로 저장합니다.

``` Java
public class CompileAndRun {
    public static void main(String[] args) {
        System.out.println("Java Study with Whiteship");
    }
}
```

저장된 java 파일을 컴파일하는 방법은 아래와 같으며 결과로 바이트코드로 컴파일된 CompileAndRun.class 파일을 확인할 수 있습니다.

```cmd
$ {JDK_HOME}/bin/javac ./CompileAndRun.java
$ ls
CompileAndRun.java CompileAndRun.class
```

---

### 실행하는 방법

> JDK 또는 JRE bin 하위 폴더에 있는 java를 사용하여 실행

실행은 위에서 생성한 class 파일을 아래와 같이 실행할 수 있습니다. 이 때 컴파일과는 달리 실행은 JDK, JRE 둘 중 어느것을 이용하든 정상적으로 실행이 됩니다. 단, 실행을 할 때에는 확장자명을 제외하고 실행해야 합니다.

```cmd
$ {JDK_HOME}/bin/java ./CompileAndRun
Java Study with Whiteship
```

이렇게 자바 파일을 컴파일부터 실행하는 과정까지 확인할 수 있었는데 여기에서 주의할 점은 자바 파일을 컴파일 할 때 상위 버전(JDK11)의 JDK 로 컴파일한 .class 파일은 하위 버전(JDK1.8)의 JDK 또는 JRE로 실행할 수 없다는 점입니다. (반대로 하위 버전에서 컴파일한 .class 파일은 상위 버전의 JDK 또는 JRE로 실행할 수 있습니다.)

---

### 바이트코드란 무엇인가

바이트코드는 자바의 WORA를 구현하기 위해 사용되는 중간 언어입니다. 자바 컴파일러는 사람이 이해하는 Java 언어를 운영체제가 해석할 수 있는 기계어로 변환하는 것이 아닌 JVM이 해석할 수 있는 코드로 변환하며 이때 변환된 언어가 바이트코드입니다. 따라서 바이트코드는 운영체제에 종속적이지 않으며, 그로인해 Windows에서 자바 언어를 컴파일하여 바이트코드로 변환된 클래스 파일을 Linux에서 변경없이 실행이 가능하게 됩니다.

---

### JIT 컴파일러란 무엇이며 어떻게 동작하는가

JVM은 바이트코드를 운영체제에서 실행할 수 있는 형태로 변경하는데 이때 사용하는 방식은 인터프리터 방식과 JIT 컴파일러 방식 두 가지가 있습니다.

인터프리터 방식은 바이트코드 명령어를 하나씩 읽어서 해석하고 실행합니다. 인터프리터 방식은 바이트코드의 기본적인 동작 방식입니다.

JIT(Just-In-Time) 컴파일러는 인터프리터의 단점을 보완하기 위해 도입된 것입니다. 인터프리터 방식은 동일한 코드도 다시 해석하고 실행한다는 단점이 있습니다. 이런 단점을 보완하기 JIT 컴파일러는 적절한 시점에 바이트코드 전체를 컴파일하여고 네이티브 코드로 변환합니다. 변환한 네이티브 코드를 캐시에 보관하고 캐쉬에 있는 내용을 통해 빠르게 실행할 수 있게 했습니다.

JIT 컴파일러가 컴파일하는 과정은 인터프리팅 방식보다 오래 걸립니다. 그래서 한 번만 실행되는 코드라면 컴파일을 하지 않도록 내부적으로 해당 코드가 얼마나 자주 실행되는지 확인하고, 일정 수를 넘을 경우에만 컴파일을 수행합니다. 

---

### JVM 구성 요소

![JVM구조](https://raw.githubusercontent.com/Kim-JunHyeong/java-study/main/images/JVM%20structure.png)

- 이미지 및 내용 출처 : [https://www.inflearn.com/course/the-java-code-manipulation](https://www.inflearn.com/course/the-java-code-manipulation)

JVM의 구조는 크게 클래스 로더 시스템, 메모리, 실행 엔진, 네이티브 메소드 인터페이스(JNI), 네이티브 메소드 라이브러리 5가지로 나눌 수 있으며 각각의 역할은 다음과 같습니다.

- 클래스 로더 시스템
  : 바이트코드를 읽어들여서 메모리에 적절하게 배치하는 역할을 하며, 크게 3가지의 역할을 수행합니다.
  - 로딩(loading) 
    : 클래스 파일에서 바이트코드를 읽어오는 과정입니다.
  - 링크(linking) 
    : 심볼릭 메모리 레퍼런스를 메소드 영역에 있는 실제 레퍼런스로 교체합니다.
  - 초기화(initialization) 
    : static 변수의 값을 할당합니다.
- 메모리 (Runtime Data Area)
  : JVM이 운영체제 위에서 실행되면서 할당받는 메모리 영역입니다.
  - 스택, PC, 네이티브 메소드 스택 
    : 스레드마다 하나씩 생성
  - 힙, 메소드
    : 모든 스레드가 공유해서 사용
- 실행 엔진
  : 클래스 로더를 통해 JVM 내의 메모리 영역에 배치된 바이트코드는 실행 엔진에 의해 실행됩니다.
  - 인터프리터
  - JIT 컴파일러
  - GC(Garbage Collection)
- 네이티브 메소드 인터페이스(JNI)
  - 네이티브 프로그래밍 인터페이스로 JVM 내에서 실행되는 Java 코드가 C, C++, 어셈블리와 같은 다른 프로그래밍 언어로 작성된 라이브러리와 상호 작용 할 수 있도록 해줍니다.
- 네이티브 메소드 라이브러리

---

### JDK와 JRE의 차이점

> JRE = JVM + 표준 클래스 라이브러리
> JDK = JRE + 개발에 필요한 도구

JDK(Java Development Kit)와 JRE(Java Runtime Environment)는 Java SE의 구현체입니다. 여기서 JRE는 프로그램 실행에 필요한 JVM, 표준 클래스 라이브러리 API 만 포함되어 있고 JDK는 바이트코드를 생성하기 위한 컴파일러, JRE를 포함한 개발 도구를 가지고 있습니다.

결국, 자바 프로그램을 실행하기만 할 때에는 JRE만 설치가 되어 있으면 충분하며 더 나아가 자바 프로그램을 개발하기 위해서는 JDK 를 설치하면 됩니다.

![JDK와 JRE](https://raw.githubusercontent.com/Kim-JunHyeong/java-study/main/images/JDK%20and%20JRE.PNG)

---

### 마치며

해당 글은 백기선님이 토요일 밤에 진행하시는 Java Study 내용입니다. 계획상으로는 18주 차까지 이루어져 있는데 1주 차에 관련된 내용을 뒤늦게나마 작성하였습니다.

스스로 정리가 덜 된 부분에 대해서는 계속 학습하고 해당 내용을 계속해서 수정해나가도록 하겠습니다. 

어떤 공부를 하던 배운 내용을 정리하는 것에 대한 중요성은 꾸준히 들어왔지만 항상 시작하고 얼마 못 가서 포기하고 말았었습니다. 그래서 이번 스터디를 포기하지 않고 끝까지 진행하는 것을 목표로 정리하는 습관을 갖도록 하려 합니다. 이런 핑계로나마 시작할 수 있게 기회를 만들어주신 백기선님 감사합니다!

아직 말로 정리하는 것도 글로 정리하는 것도 미숙하여 내용 중간중간마다 정리하는 방식이 바뀔 수 있지만 그래도 일단 정리하는 것에 의의를 두기로 했습니다. (신경을 쓰다 보면 글 작성을 못해서...)

---

### 참고  문헌

- [https://www.inflearn.com/course/the-java-code-manipulation](https://www.inflearn.com/course/the-java-code-manipulation)
- [https://github.com/whiteship/live-study/issues/1](https://github.com/whiteship/live-study/issues/1)
- [https://d2.naver.com/helloworld/1230](https://d2.naver.com/helloworld/1230)
- [https://www.hanbit.co.kr/store/books/look.php?p_code=B1460673937](https://www.hanbit.co.kr/store/books/look.php?p_code=B1460673937)
