package com.back.boundedContext.member.domain;


import com.back.shared.member.domain.SourceMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 초기화 클래스
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name="MEMBER_MEMBER")
public class Member extends SourceMember {
    public void increasePoint(int amount) {
        setActivityScore(getActivityScore() + amount);
    }


    public Member(String username, String password, String nickname) {
        super(username, password, nickname);
    }
}
