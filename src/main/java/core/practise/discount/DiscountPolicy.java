package core.practise.discount;

import core.practise.member.Member;

/**
 * 할인 정책 역할
 */
public interface DiscountPolicy {
    /**
     * @return 상품의 할인 금액
     */
    int discount(Member member, int itemPrice);
}
