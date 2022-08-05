package dev.practice.gift.domain.gift.order;

public interface OrderApiCaller {
    String registerGiftOrder(OrderApiCommand.Register request);

    void updateReceiverInfo();
}
