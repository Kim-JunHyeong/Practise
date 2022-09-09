package core.practise.order;

/**
 * 주문 서비스 역할
 */
public interface OrderService {
    /**
     * @return 주문 결과
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
