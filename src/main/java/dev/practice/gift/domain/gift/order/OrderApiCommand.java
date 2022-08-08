package dev.practice.gift.domain.gift.order;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class OrderApiCommand {

    @Getter @Builder
    public static class Register {
        private Long buyerUserId;
        private String payMethod;
        private List<RegisterOrderItem> orderItemList;
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
        private String itemOptionGroupName;
        private List<RegisterOrderItemOption> orderItemOptionList;
    }

    @Getter @Builder
    public static class RegisterOrderItemOption {
        private Integer ordering;
        private String itemOptionName;
        private Long itemOptionPrice;
    }
}
