package dev.practice.gift.infrastructure.gift;

import dev.practice.gift.common.exception.EntityNotFoundException;
import dev.practice.gift.common.exception.InvalidParamException;
import dev.practice.gift.domain.gift.Gift;
import dev.practice.gift.domain.gift.GiftReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class GiftReaderImpl implements GiftReader {

    private final GiftRepository giftRepository;

    @Override
    public Gift getGiftBy(String giftToken) {
        if (StringUtils.isEmpty(giftToken)) throw new InvalidParamException();
        return giftRepository.findByGiftToken(giftToken).orElseThrow(EntityNotFoundException::new);
    }
}
