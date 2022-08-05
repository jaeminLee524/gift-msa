package dev.practice.gift.domain.gift;

import lombok.Getter;

@Getter
public class GiftInfo {

    private String orderToken;
    private String giftToken;
    private Gift.PushType pushType;
    private String giftReceiverName;
    private String giftReceiverPhone;
    private String giftMessage;

    public GiftInfo(Gift gift) {
        this.orderToken = gift.getOrderToken();
        this.giftToken = gift.getGiftToken();
        this.pushType = gift.getPushType();
        this.giftReceiverName = gift.getGiftReceiverName();
        this.giftReceiverPhone = gift.getGiftReceiverPhone();
        this.giftMessage = gift.getGiftMessage();
    }
}
