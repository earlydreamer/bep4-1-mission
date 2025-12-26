package com.back.shared.post.dto;

import com.back.boundedContext.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MemberUpdatedEventPayload {
    private final int id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String nickname;
    private final String username;
    private final int activityScore;

    public MemberUpdatedEventPayload(Member member){
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
