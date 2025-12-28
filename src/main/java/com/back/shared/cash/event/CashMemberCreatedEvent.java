package com.back.shared.cash.event;

import com.back.shared.cash.dto.CashMemberCreatedEventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CashMemberCreatedEvent {
    private final CashMemberCreatedEventPayload member;

}
