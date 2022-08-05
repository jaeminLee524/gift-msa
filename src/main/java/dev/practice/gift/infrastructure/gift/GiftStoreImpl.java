package dev.practice.gift.infrastructure.gift;

import dev.practice.gift.common.exception.InvalidParamException;
import dev.practice.gift.domain.gift.Gift;
import dev.practice.gift.domain.gift.GiftStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GiftStoreImpl implements GiftStore {

    private final GiftRepository giftRepository;
    @Override
    public Gift store(Gift initGift) {
        if (initGift == null) throw new InvalidParamException();

        return giftRepository.save(initGift);
    }
}
