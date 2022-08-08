package dev.practice.gift.interfaces.gift;

import dev.practice.gift.application.gift.GiftFacade;
import dev.practice.gift.common.response.CommonResponse;
import dev.practice.gift.domain.gift.GiftCommand;
import dev.practice.gift.domain.gift.GiftInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifts")
@RestController
public class GiftApiController {

    private final GiftFacade giftFacade;
    private final GiftDtoMapper giftDtoMapper;

    // 선물 주문 정보 조회
    @GetMapping("/{giftToken}")
    public CommonResponse retrieveOrder(@PathVariable("giftToken") String giftToken) {
        GiftInfo giftInfo = giftFacade.retrieveOrder(giftToken);
        //TODO: GiftInfo -> GiftDto.RetrieveOrderRes

        return CommonResponse.success(giftDtoMapper.giftInfoToRetrieveRes(giftInfo));
    }

    // 선물하기 주문 저장
    @PostMapping
    public CommonResponse registerOrder(@RequestBody @Valid GiftDto.RegisterReq request) {
        GiftCommand.Register command = giftDtoMapper.of(request);
        GiftInfo giftInfo = giftFacade.registerOrder(command);

        return CommonResponse.success(giftDtoMapper.of(giftInfo));
    }

    // 결제 처리 상태를 변경
    @PostMapping("/{giftToken}/payment-processing")
    public CommonResponse requestPaymentProcessing(@PathVariable("giftToken") String giftToken) {
        giftFacade.requestPaymentProcessing(giftToken);

        return CommonResponse.success("ok");
    }

    // 선물 수락
    @PostMapping("/{giftToken}/accept-gift")
    public CommonResponse acceptGift(@PathVariable("giftToken")String giftToken,
                                     @RequestBody @Valid GiftDto.AcceptGiftReq request) {
        // dto -> command
        GiftCommand.AcceptGift acceptCommand = giftDtoMapper.of(giftToken, request);
        giftFacade.acceptGift(acceptCommand);

        return CommonResponse.success("ok");
    }
}
