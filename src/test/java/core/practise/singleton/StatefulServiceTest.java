package core.practise.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자가 10_000원 주문
        statefulService1.order("userA", 10_000);
        // ThreadB : B 사용자가 20_000원 주문
        statefulService2.order("userB", 20_000);

        // ThreadA : A 사용자가 주문 금액 조회
        int price = statefulService1.getPrice();
        // ThreadA : A 사용자는 10_000원을 기대했지만, 기대와 다르게 20_000원 출력
        System.out.println("price = " + price);

        assertThat(statefulService1.getPrice()).isEqualTo(20_000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}