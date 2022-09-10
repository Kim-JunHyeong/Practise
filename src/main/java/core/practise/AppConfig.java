package core.practise;

import core.practise.discount.FixDiscountPolicy;
import core.practise.member.MemberService;
import core.practise.member.MemberServiceImpl;
import core.practise.member.MemoryMemberRepository;
import core.practise.order.OrderService;
import core.practise.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
