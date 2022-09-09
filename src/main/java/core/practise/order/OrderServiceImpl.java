package core.practise.order;

import core.practise.discount.DiscountPolicy;
import core.practise.member.Member;
import core.practise.member.MemberRepository;
import core.practise.member.MemoryMemberRepository;

/**
 * 주문 서비스 구현체
 */
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 1. 정책 변경
    private DiscountPolicy discountPolicy; // 2. 인터페이스에만 의존하도록 변경

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
