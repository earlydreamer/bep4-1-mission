package com.back.shared.post.event;

import com.back.shared.post.dto.MemberUpdatedEventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdatedEvent {
    private final MemberUpdatedEventPayload member;
}
