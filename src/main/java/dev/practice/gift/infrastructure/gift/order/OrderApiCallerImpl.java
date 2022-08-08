package dev.practice.gift.infrastructure.gift.order;

import dev.practice.gift.common.response.CommonResponse;
import dev.practice.gift.domain.gift.GiftCommand;
import dev.practice.gift.domain.gift.order.OrderApiCaller;
import dev.practice.gift.domain.gift.order.OrderApiCommand;
import dev.practice.gift.infrastructure.retrofit.RetrofitUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderApiCallerImpl implements OrderApiCaller {

    private final RetrofitUtils retrofitUtil;
    private final RetrofitOrderApi retrofitOrderApi;


    @Override
    public String registerGiftOrder(OrderApiCommand.Register request) {
        var call = retrofitOrderApi.registerOrder(request);
        return retrofitUtil.responseSync(call)
                .map(CommonResponse::getData)
                .map(RetrofitOrderApiResponse.Register::getOrderToken)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void updateReceiverInfo(String orderToken, GiftCommand.AcceptGift acceptGiftCommand) {
        Call<Void> call = retrofitOrderApi.updateReceiverInfo(orderToken, acceptGiftCommand);
        retrofitUtil.responseVoid(call);
    }
}
