package core.practise.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Order {
    /** 회원 ID */
    private Long memberId;
    /** 상품명 */
    private String itemName;
    /** 상품 금액 */
    private int itemPrice;
    /** 할인 금액 */
    private int discountPrice;


    /**
     * @return 할인된 상품의 가격
     */
    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
