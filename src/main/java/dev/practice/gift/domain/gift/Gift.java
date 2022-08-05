package dev.practice.gift.domain.gift;

import dev.practice.gift.common.exception.InvalidParamException;
import dev.practice.gift.common.util.TokenGenerator;
import dev.practice.gift.domain.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "gifts")
public class Gift extends AbstractEntity {

    private static final String GIFT_PREFIX = "gt_";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String giftToken;
    private Long buyerUserId;
    private String orderToken;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Enumerated(value = EnumType.STRING)
    private PushType pushType;

    private String giftReceiverName;
    private String giftReceiverPhone;
    private String giftMessage;
    private String receiverName;
    private String receiverPhone;
    private String receiverZipcode;
    private String receiverAddress1;
    private String receiverAddress2;
    private String etcMessage;

    private ZonedDateTime paidAt;
    private ZonedDateTime pushedAt;
    private ZonedDateTime acceptedAt;
    private ZonedDateTime expiredAt;

    @Getter
    @AllArgsConstructor
    public enum Status {
        INIT("선물 주문 생성"),
        IN_PAYMENT("결제 중"),
        ORDER_COMPLETE("주문 완료"),
        PUSH_COMPLETE("선물 링크 발송 완료"),
        ACCEPT("선물 수락"),
        DELIVERY_PREPARE("상품 준비"),
        IN_DELIVERY("배송 중"),
        DELIVERY_COMPLETE("배송 완료"),
        EXPIRATION("선물 수락 만료 "),
        ;

        private final String description;
    }

    @Getter
    @AllArgsConstructor
    public enum PushType {
        KAKAO("카카오톡"),
        LMS("문자");

        private final String description;
    }

    public Gift(Long buyerUserId, String orderToken, PushType pushType, String giftReceiverName, String giftReceiverPhone, String giftMessage) {
        this.buyerUserId = buyerUserId;
        this.giftToken = TokenGenerator.randomCharacterWithPrefix(GIFT_PREFIX);
        this.orderToken = orderToken;
        this.status = Status.INIT;
        this.pushType = pushType;
        this.giftReceiverName = giftReceiverName;
        this.giftReceiverPhone = giftReceiverPhone;
        this.giftMessage = giftMessage;
        this.expiredAt = ZonedDateTime.now().plusDays(7);
    }

    public static final Gift of(Long buyerUserId, String orderToken, PushType pushType, String giftReceiverName, String giftReceiverPhone, String giftMessage) {
        if (buyerUserId == null) throw new InvalidParamException("Gift constructor buyerUserId is null");
        if (pushType == null) throw new InvalidParamException("Gift constructor pushType is null");
        if (StringUtils.isEmpty(giftReceiverName)) throw new InvalidParamException("Gift constructor giftReceiverName is empty");
        if (StringUtils.isEmpty(giftReceiverPhone)) throw new InvalidParamException("Gift constructor giftReceiverPhone is empty");
        if (StringUtils.isEmpty(giftMessage)) throw new InvalidParamException("Gift constructor giftMessage is empty");

        return new Gift(buyerUserId, orderToken, pushType, giftReceiverName, giftReceiverPhone, giftMessage);
    }

}
