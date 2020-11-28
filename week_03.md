# 3주차 : 연산자

---

## 학습 목표

자바가 제공하는 다양한 연산자를 학습

## 목차

- 산술 연산자
- 비트 연산자
- 관계 연산자
- 논리 연산자
- instanceof
- assignment(=) operator
- 화살표(->) 연산자
- 3항 연산자
- 연산자 우선 순위
- (optional) Java 13. switch 연산자

---

### 산술 연산자

- 아래와 같은 5개의 산술 연산자가 있습니다.

| 연산자 | 설명          |
| ------ | ------------- |
| ``+``  | 덧셈 연산자   |
| ``-``  | 뺄셈 연산자   |
| ``*``  | 곱셈 연산자   |
| ``/``  | 나눗셈 연산자 |
| ``%``  | 나머지 연산자 |

- 산술 연산자는 피연산자들의 타입이 동일하지 않을 경우 다음과 같은 규칙을 사용해서 피연산자들의 타입을 일치시킨 후 연산을 수행합니다.

  1. 피연산자 모두 정수 타입, ``int`` 타입(4byte)보다 크기가 작은 타입일 경우 모두 ``int`` 타입으로 변환 후 연산을 수행 (결과는 ``int`` 타입)
     ex) ``byte + byte`` → ``int + int = int``
  ``` java
  // 컴파일 에러
  byte byte1 = 1;
  byte byte2 = 2;
  byte result = byte1 + byte2;
  // 정상
  byte byte1 = 1;
  byte byte2 = 2;
  int result = byte1 + byte2;		// 3
  ```


  2. 피연산자 모두 정수 타입, ``long`` 타입이 있을 경우 모두 ``long`` 타입으로 변환 후 연산 수행(결과는 ``long`` 타입)
     ex) ``int + long`` → ``long + long = long``

  ``` java
  // 컴파일 에러
  int int1 = 1;
  long long1 = 1L;
  int result = int1 - long1;
  // 정상
  int int1 = 1;
  long long1 = 1L;
  long result = int1 - long1;		// 0
  ```

  3. 피연산자 중 실수 타입(``float``, ``double``)이 있을 경우, 크기가 큰 실수 타입으로 변환 후 연산 수행
     ex) ``int + double`` → ``double + double = double``

  ``` java
  // int , float
  int int1 = 1;
  float float1 = 1.0F;
  float result1 = int1 / float1;		// 1.0
  // int, double
  int int2 = 2;
  double double1 = 3.0;
  double result2 = int2 % double1;	// 2.0
  ```

- ``/`` 또는 ``%`` 연산자를 사용할 때 주의할 점은 좌측 피연산자가 정수 타입인 경우 우측 피연산자는 ``0``을 사용할 수 없습니다. 만일 ``0``으로 나누면 컴파일은 되지만, 실행 시 ``ArithmeticException``이 발생합니다.
  하지만 실수 타입인 ``0.0``, ``0.0f`` 로 나누면 ``ArithmeticException``은 발생하지 않지만 나온 결과로 연산을 수행해서는 안 됩니다.

``` java
// 정수 타입일 경우
int i = 7 / 0; 		// ArithmeticException
int j = 5 % 0; 		// ArithmeticException

// 실수 타입일 경우
double x = 7 / 0.0;		
System.out.println(x);	// Infinity
float y = 7 % 0.0f;		
System.out.println(y);	// NaN

// Infinity, NaN 은 Double, Float 에 있는 isInfinity, isNaN 메소드를 사용해서 판별
System.out.println(Double.isInfinite(x));	// true
System.out.println(Float.isNaN(y));			// true
```



---

### 관계 연산자

- 비교 연산자라고도 하며 ``boolean`` 타입인 ``true / false`` 를 결과로 갖습니다.

|   구분    | 연산자 |             설명              |
| :-------: | :----: | :---------------------------: |
| 동등 비교 |   ==   |     값이 서로 같은지 비교     |
|           |   !=   |     값이 서로 다른지 비교     |
| 크기 비교 |   >    |     좌측값이 큰지를 비교      |
|           |   >=   | 좌측값이 크거나 같은지를 비교 |
|           |   <    |     우측값이 큰지를 비교      |
|           |   <=   | 우측값이 크거나 같은지를 비교 |

- 대소 연산자(구분 : 크기 비교)는 ``boolean`` 타입을 제외한 기본 타입에 사용할 수 있고, 동등 연산자는 모든 타입에 사용할 수 있습니다.

- 주로 실행 흐름을 제어할 때 많이 사용합니다.

  

---

### 논리 연산자

- 관계 연산자처럼 ``boolean`` 타입인 ``true / false`` 를 결과로 갖습니다.

#### AND (논리곱) - &&, &

- 피연산자 모두가 ``true`` 일 경우에만 결과로 ``true`` 가 나오며, 그 외에는 ``false`` 입니다.
- ``&&`` 연산자는 ``&`` 연산자와는 다르게 앞의 연산자가 false 이면 뒤의 피 연산자를 평가하지 않고 바로 ``false`` 라는 결과를 리턴합니다.

|     연산식     | 결과  |
| :------------: | :---: |
|  true && true  | true  |
| true && false  | false |
| false && true  | false |
| false && false | false |

#### OR(논리합) - || , |

- 피연산자 중 하나라도 ``true`` 일 경우 결과는 ``true`` 이며, 모두 ``false`` 일 경우 결과는 ``false`` 입니다.
- ``||`` 연산자는 ``|`` 연산자와는 다르게 앞의 연산자가 true 이면 뒤의 피 연산자를 평가하지 않고 바로 ``true`` 라는 결과를 리턴합니다.

|      연산식      | 결과  |
| :--------------: | :---: |
|  true \|\| true  | true  |
| true \|\| false  | true  |
| false \|\| true  | true  |
| false \|\| false | false |

#### XOR(배타적논리합) - ^

- 피연산자가 하나는 ``true``, 다른 하나는 ``false``일 경우에만 ``true`` 를 리턴, 그 외의 경우 ``false``를 리턴합니다.

|    연산식     | 결과  |
| :-----------: | :---: |
|  true ^ true  | false |
| true ^ false  | true  |
| false ^ true  | true  |
| false * false | false |

#### NOT(논리부정) - !

- 피연산자의 논리값(``true, false``)을 서로 바꿉니다.

| 연산식  | 결과  |
| :-----: | :---: |
| ! true  | false |
| ! false | true  |

---

### instanceof

``` java
객체 instanceof 클래스
```

- 객체가 참조하고 있는 실제 타입이 맞는지 알아내는 연산자입니다.
- 연산의 결과로 ``true`` , ``false`` 를 리턴합니다.

```java
Integer i = 1;

if (i instanceof Number) {
    System.out.println("i 는 Number 타입이다");
} else {
    System.out.println("i 는 Number 타입이 아니다");
}
```



---

### assignment(=) operator

- 대입 연산자로써, 우측의 값을 좌측의 **변수에 값을 할당한다는 의미**입니다.

``` java
// 타입 변수 = 값;
int size = 57;
```

- ``=`` 연산자는 다른 연산자와 결합하여 복합 대입 연산자로 사용할 수 있습니다. 

```java
int number = 7;

// 산술 연산자
number += 1; 		// number = number + 1
number -= 2; 		// number = number - 2
number *= 3; 		// number = number * 3
number /= 4; 		// number = number / 4
number %= 5; 		// number = number % 5

// 비트 연산자
number &= 1; 		// number = number & 1
number |= 2; 		// number = number | 2
number ^= 3; 		// number = number ^ 3

// 시프트 연산자
number <<= 1; 		// number = number << 1
number >>= 2; 		// number = number >> 2
number >>>= 3; 		// number = number >>> 3
```



---

### 화살표(->) 연산자

- JDK 1.8 부터 추가된 람다 표현식을 지원하기 위해 추가된 연산자입니다.
  - 람다 표현식이란 메소드를 하나의 식으로 표현한 것을 말합니다.
- 람다식의 기본 작성법은 아래와 같습니다.

``` java
(매개변수) -> {실행문}
```



---

### 3항 연산자

- 세 개의 피연산자가 필요로 하는 연산자를 말합니다.
- 조건식을 연산하여 ``TRUE`` 가 나오면 결과는 피연산자2
- 조건식을 연산하여 ``FALSE`` 가 나오면 결과는 피연산자3

![삼항 연산자](D:\Users\CrAzy\Desktop\삼항 연산자.PNG)

- 삼항 연산자는 if 문으로 작성할 수 있지만 간단한 연산은 삼항 연산자를 사용하는 것이 더 효율적입니다.

```JAVA
// 3항 연산자 미사용
public int max(int x, int y) {
    if (x > y) {
        return x
    } else {
        return y
    }
}
// 3항 연산자 사용
public int max(int x, int y) {
    return x > y ? x : y
}
```



---

### 연산자 우선 순위

- 복잡한 연산식은 괄호 ( )를 사용해서 먼저 처리할 연산식을 묶어주는 것이 좋습니다.
- 연산자는 우선순위와 연산 방향이 정해져 있으며 우선 순위는 아래와 같습니다.

| 연산자                                     | 연산방향 | 우선순위 |
| ------------------------------------------ | -------- | -------- |
| 증감(++, --), 부호(+, -), 비트(~), 논리(!) | ←        | 1        |
| 산술(*, /, %) | →        | 2        |
| 산술(+, -) | →        | 3        |
| 쉬프트(<<, >>, >>>) | →        | 4        |
| 비교(<, >, <=, >=, instanceof) | →        | 5        |
| 비교(==, !=) | →        | 6        |
| 논리(&) | →        | 7        |
| 논리(^) | →        | 8        |
| 논리(\|) | →        | 9        |
| 논리(&&) | →        | 10       |
| 논리(\|\|) | →        | 11       |
| 조건(? :) | →        | 12       |
| 대입(=, +=, -=, *=, /=, %=, &=, ^=, !=, <<=, >>=, >>>=) | ←       | 13       |

---

### Java 13. switch 연산자

- Java 12 에서 ``switch`` 문이 확장되어 추가적인 기능이 생겼습니다.



---

### 참조

- [이것이 자바다](https://www.hanbit.co.kr/store/books/look.php?p_code=B1460673937)

