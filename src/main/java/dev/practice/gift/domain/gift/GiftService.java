package dev.practice.gift.domain.gift;

public interface GiftService {

    GiftInfo registerOrder(GiftCommand.Register command);

    void requestPaymentProcessing(String giftToken);

    void acceptGift(GiftCommand.AcceptGift acceptCommand);

    GiftInfo getGiftInfo(String giftToken);
}
