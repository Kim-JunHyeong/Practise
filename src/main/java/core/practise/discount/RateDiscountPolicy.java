package core.practise.discount;

import core.practise.member.Grade;
import core.practise.member.Member;

/**
 * 정률 할인 정책 구현체
 */
public class RateDiscountPolicy implements DiscountPolicy {

    private final int DISCOUNT_PERCENT = 10; // 10% 할인

    @Override
    public int discount(Member member, int itemPrice) {
        if (member.getGrade() == Grade.VIP) {
            return itemPrice * DISCOUNT_PERCENT / 100;
        } else {
            return 0;
        }
    }
}
