package dev.practice.gift.domain.gift;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class GiftCommand {

    @Getter @Builder
    public static class Register {
        private Long buyerUserId;
        private String payMethod;
        private String pushType;
        private String registerReceiverName;
        private String registerReceiverPhone;
        private String giftMessage;
        private List<RegisterOrderItem> orderItemList;

        public Gift toEntity(String orderToken) {
            return Gift.of(buyerUserId, orderToken, Gift.PushType.valueOf(pushType), registerReceiverName, registerReceiverPhone, giftMessage);
        }
    }

    @Getter @Builder
    public static class RegisterOrderItem {
        private Integer orderCount;
        private String itemToken;
        private String itemName;
        private Long itemPrice;
        private List<RegisterOrderItemOptionGroup> orderItemOptionGroupList;
    }

    @Getter @Builder
    public static class RegisterOrderItemOptionGroup {
        private Integer ordering;
        private String itemOptionName;
        private List<RegisterOrderItemOption> orderItemOptionList;
    }

    @Getter @Builder
    public static class RegisterOrderItemOption {
        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;
    }

}
