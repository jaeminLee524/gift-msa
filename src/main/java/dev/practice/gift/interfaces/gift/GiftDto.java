package dev.practice.gift.interfaces.gift;

import dev.practice.gift.domain.gift.GiftInfo;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class GiftDto {

    @Getter @Builder
    public static class RegisterReq {
        @NotNull(message = "userId 는 필수값입니다")
        private Long buyerId;

        @NotBlank(message = "payMethod 는 필수값입니다")
        private String payMethod;

        @NotBlank(message = "pushType 는 필수값입니다")
        private String pushType;

        @NotBlank(message = "giftReceiverName 는 필수값입니다")
        private String registerReceiverName;

        @NotBlank(message = "giftReceiverPhone 는 필수값입니다")
        private String registerReceiverPhone;

        @NotBlank(message = "giftMessage 는 필수값입니다")
        private String giftMessage;

        private List<RegisterOrderItemReq> orderItemList;
    }

    @Getter @Builder
    public static class RegisterOrderItemReq {
        @NotNull(message = "orderCount 는 필수값입니다")
        private Integer orderCount;

        @NotBlank(message = "itemToken 는 필수값입니다")
        private String itemToken;

        @NotBlank(message = "itemName 는 필수값입니다")
        private String itemName;

        @NotNull(message = "itemPrice 는 필수값입니다")
        private Long itemPrice;

        private List<RegisterOrderItemOptionGroupReq> orderItemOptionGroupList;
    }

    @Getter @Builder
    public static class RegisterOrderItemOptionGroupReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        private String itemOptionGroupName;
        private List<RegisterOrderItemOptionReq> orderItemOptionReqList;
    }

    @Getter
    @Builder
    public static class RegisterOrderItemOptionReq {
        @NotNull(message = "ordering 는 필수값입니다")
        private Integer ordering;

        @NotBlank(message = "itemOptionName 는 필수값입니다")
        private String itemOptionName;

        @NotNull(message = "itemOptionPrice 는 필수값입니다")
        private Long itemOptionPrice;
    }


    public static class RegisterRes {
        private String orderToken;
        private String giftToken;

        public RegisterRes(GiftInfo giftInfo) {
            this.orderToken = giftInfo.getOrderToken();
            this.giftToken = giftInfo.getGiftToken();
        }
    }
}
