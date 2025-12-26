package com.back.shared.member.event;

import com.back.shared.member.dto.MemberJoinedEventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinedEvent {
    private final MemberJoinedEventPayload member;

}
