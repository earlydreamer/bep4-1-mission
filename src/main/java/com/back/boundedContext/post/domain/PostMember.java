package com.back.boundedContext.post.domain;

import com.back.global.jpa.entity.BaseIdAndTimeManual;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="POST_MEMNBER")
@Getter
public class PostMember extends BaseIdAndTimeManual {
    @Column(unique=true)
    private String username;
    private String password;
    private String nickname;
    @Column(nullable = false)
    private long point = 0L; // 초기값 설정

}
