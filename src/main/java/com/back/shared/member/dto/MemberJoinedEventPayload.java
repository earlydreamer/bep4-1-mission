package com.back.shared.member.dto;

import com.back.boundedContext.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberJoinedEventPayload {

    //비밀번호는 민감정보이므로 제외
    // score 정보는 가입시 0점 고정이므로 넘기지 않아도 된다.
    private final int id;
    private final String username;
    private final String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public MemberJoinedEventPayload(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.nickname = member.getNickname();
        this.createdAt = member.getCreatedAt();
        this.updatedAt = member.getUpdatedAt();
    }

}
