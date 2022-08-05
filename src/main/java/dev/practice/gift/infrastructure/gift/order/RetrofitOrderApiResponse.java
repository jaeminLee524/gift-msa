package dev.practice.gift.infrastructure.gift.order;

import lombok.Builder;
import lombok.Getter;

public class RetrofitOrderApiResponse {

    @Getter @Builder
    public static class Register {
        private String orderToken;
    }

}
