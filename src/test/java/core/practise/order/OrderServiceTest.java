package core.practise.order;

import core.practise.member.Grade;
import core.practise.member.Member;
import core.practise.member.MemberService;
import core.practise.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10_000);

        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1_000);
    }
}