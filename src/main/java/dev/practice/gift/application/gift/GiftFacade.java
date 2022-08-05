package dev.practice.gift.application.gift;

import dev.practice.gift.domain.gift.GiftCommand;
import dev.practice.gift.domain.gift.GiftInfo;
import dev.practice.gift.domain.gift.GiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GiftFacade {
    private final GiftService giftService;

    public GiftInfo registerOrder(GiftCommand.Register command) {
        GiftInfo giftInfo = giftService.registerOrder(command);
        log.info("giftInfo.orderToken={}", giftInfo);
        return giftInfo;
    }
}
