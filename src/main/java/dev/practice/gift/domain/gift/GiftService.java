package dev.practice.gift.domain.gift;

public interface GiftService {

    GiftInfo registerOrder(GiftCommand.Register command);
}
