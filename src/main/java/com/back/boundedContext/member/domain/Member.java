package com.back.boundedContext.member.domain;


import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Column;
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
public class Member extends BaseIdAndTime {

    @Column(unique=true)
    private String username;
    private String password;
    private String nickname;
    @Column(nullable = false)
    private long point = 0L; // 초기값 설정

    public void increasePoint(long score) {
        this.point += score; // 실제로 값을 증가시키는지 확인
    }


    public Member(String username,String password, String nickname)
    {
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.point=0;
    }




}
