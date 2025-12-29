package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.MarketMember;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MarketMemberCreatedEventPayload {
    private final Long id;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;
    private final String username;
    private final String nickname;
    private final int activityScore;

    public MarketMemberCreatedEventPayload(MarketMember member) {
        this(
                member.getId(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getUsername(),
                member.getNickname(),
                member.getActivityScore()
        );
    }
}
