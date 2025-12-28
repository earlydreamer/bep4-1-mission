package com.back.shared.cash.dto;

import com.back.boundedContext.cash.domain.CashMember;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CashMemberCreatedEventPayload {

    //비밀번호는 민감정보이므로 제외
    // score 정보는 가입시 0점 고정이므로 넘기지 않아도 된다.
    private final Long id;
    private final String username;
    private final String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private final int activityScore;

    public CashMemberCreatedEventPayload(CashMember cashMember) {
        this.id = cashMember.getId();
        this.username = cashMember.getUsername();
        this.nickname = cashMember.getNickname();
        this.createdAt = cashMember.getCreatedAt();
        this.updatedAt = cashMember.getUpdatedAt();
        this.activityScore = cashMember.getActivityScore();
    }

}
