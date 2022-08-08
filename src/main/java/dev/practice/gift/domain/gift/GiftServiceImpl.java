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
    private final GiftReader giftReader;

    /**
     * 선물 주문 정보를 가져온다
     * 선물 수령자의 수락 페이지 로딩 시에 사용된다
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/08/08
    **/
    @Override
    public GiftInfo getGiftInfo(String giftToken) {
        Gift findGift = giftReader.getGiftBy(giftToken);
        return new GiftInfo(findGift);
    }

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
        OrderApiCommand.Register orderCommand = giftToOrderMapper.of(command);
        String orderToken = orderApiCaller.registerGiftOrder(orderCommand);
        Gift initGift = command.toEntity(orderToken);
        Gift gift = giftStore.store(initGift);

        return new GiftInfo(gift);
    }

    /**
     * 선물하기의 주문 상태를 [결제중]으로 변경
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/08/08
    **/
    @Override
    public void requestPaymentProcessing(String giftToken) {
        Gift findGift = giftReader.getGiftBy(giftToken);
        findGift.inPayment();
    }

    /**
     * 선물 수령자가 배송지를 입력하고 [선물 수락] 하면
     * 선물 상태를 Accept 로 변경하고, 주문 서비스 API 를 호출하여 주문의 배송지 주소를 업데이트 한다
     * @author jaemin
     * @version 1.0.0
     * 작성일 2022/08/08
    **/
    @Override
    public void acceptGift(GiftCommand.AcceptGift acceptCommand) {
        Gift findGift = giftReader.getGiftBy(acceptCommand.getGiftToken());
        findGift.accept(acceptCommand);

        orderApiCaller.updateReceiverInfo(findGift.getOrderToken(), acceptCommand);
    }

}
