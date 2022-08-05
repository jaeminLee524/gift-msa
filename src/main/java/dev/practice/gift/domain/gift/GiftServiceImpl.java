package dev.practice.gift.domain.gift;

import dev.practice.gift.domain.gift.order.OrderApiCaller;
import dev.practice.gift.domain.gift.order.OrderApiCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class GiftServiceImpl implements GiftService{

    private final GiftToOrderMapper giftToOrderMapper;
    private final OrderApiCaller orderApiCaller;
    private final GiftStore giftStore;

    /**
     * 선물하기 주문을 등록
     * 해당 주문을 주문하기 서비스에 등록하기 위해 api 호출
     * 등록된 주문의 식별자와 선물 관련 정보를 반영하여 Gift 도메인을 저장한다
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/08/05
    **/
    @Override
    public GiftInfo registerOrder(GiftCommand.Register command) {
        // gift -> order
        OrderApiCommand.Register orderCommand = giftToOrderMapper.of(command);

        // order domain save
        String orderToken = orderApiCaller.registerGiftOrder(orderCommand);

        // gift command -> giftEntity
        Gift initGift = command.toEntity(orderToken);

        // gift domain save
        Gift gift = giftStore.store(initGift);

        // return GiftInfo
        return new GiftInfo(gift);
    }
}
