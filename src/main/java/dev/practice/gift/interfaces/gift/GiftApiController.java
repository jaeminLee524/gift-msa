package dev.practice.gift.interfaces.gift;

import dev.practice.gift.application.gift.GiftFacade;
import dev.practice.gift.common.response.CommonResponse;
import dev.practice.gift.domain.gift.GiftCommand;
import dev.practice.gift.domain.gift.GiftInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifts")
@RestController
public class GiftApiController {

    private final GiftFacade giftFacade;
    private final GiftDtoMapper giftDtoMapper;

    // 선물 주문 정보 조회

    // 선물하기 주문 저장
    @PostMapping
    public CommonResponse registerOrder(@RequestBody @Valid GiftDto.RegisterReq request) {
        GiftCommand.Register command = giftDtoMapper.of(request);
        GiftInfo giftInfo = giftFacade.registerOrder(command);

        return CommonResponse.success(new GiftDto.RegisterRes(giftInfo));
    }

    // 결제 처리 상태로 변경

    // 선물 수락
}
