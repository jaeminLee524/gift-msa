package dev.practice.gift.interfaces.gift;

import dev.practice.gift.domain.gift.GiftCommand;
import dev.practice.gift.domain.gift.GiftInfo;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface GiftDtoMapper {

    /** request **/
    GiftCommand.Register of(GiftDto.RegisterReq req);

    GiftCommand.RegisterOrderItem of(GiftDto.RegisterOrderItemReq req);

    GiftCommand.RegisterOrderItemOptionGroup of(GiftDto.RegisterOrderItemOptionGroupReq req);

    GiftCommand.RegisterOrderItemOption of(GiftDto.RegisterOrderItemOptionReq req);

    GiftCommand.AcceptGift of(String giftToken, GiftDto.AcceptGiftReq request);


    /** response **/
    GiftDto.RegisterRes of(GiftInfo giftInfo);
    GiftDto.RetrieveOrderRes giftInfoToRetrieveRes(GiftInfo giftInfo);


}
