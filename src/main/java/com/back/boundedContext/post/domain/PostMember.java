package com.back.boundedContext.post.domain;

import com.back.shared.member.domain.ReplicaMember;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="POST_MEMBER")
@Getter
public class PostMember extends ReplicaMember {
    @Column(unique=true)
    private String username;
    private String password;
    private String nickname;
    @Column(nullable = false)
    private int activityScore = 0; // 초기값 설정

}
