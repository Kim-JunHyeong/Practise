package core.practise;

import core.practise.member.Grade;
import core.practise.member.Member;
import core.practise.member.MemberService;
import core.practise.member.MemberServiceImpl;
import core.practise.order.Order;
import core.practise.order.OrderService;
import core.practise.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10_000);

        System.out.println("order = " + order);
    }
}
