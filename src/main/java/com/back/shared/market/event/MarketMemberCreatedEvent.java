package com.back.shared.market.event;

import com.back.shared.market.dto.MarketMemberCreatedEventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MarketMemberCreatedEvent {
    private final MarketMemberCreatedEventPayload member;
}
