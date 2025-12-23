
package com.back.entity;


import com.back.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 초기화 클래스
 */
@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseIdAndTime {

    @Column(unique=true)
    private String username;
    private String password;
    private String nickname;
    private long point;


    /**
     * 의도적으로 결합도 발생시킨 구조
     * @param amount
     * @return
     */
    public long increasePoint(long amount) {
        return this.point += amount;
    }


    public Member(String username,String password, String nickname)
    {
        this.username=username;
        this.password=password;
        this.nickname=nickname;
        this.point=0;
    }




}
