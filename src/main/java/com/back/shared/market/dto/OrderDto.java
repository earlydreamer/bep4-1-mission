package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 주문 정보를 표현하는 DTO
 *
 * <p>주문 조회/응답에 사용됩니다. TODO: 필드별 의미와 null 가능성 문서화
 */
@AllArgsConstructor
@Getter
public class OrderDto {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final Long customerId;
    private final String customerName;
    private final long price;
    private final long salePrice;
    private final LocalDateTime requestPaymentDate;
    private final LocalDateTime paymentDate;

    public OrderDto(Order order) {
        this(
                order.getId(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getBuyer().getId(),
                order.getBuyer().getNickname(),
                order.getPrice(),
                order.getSalePrice(),
                order.getRequestPaymentDate(),
                order.getPaymentDate()
        );
    }
}