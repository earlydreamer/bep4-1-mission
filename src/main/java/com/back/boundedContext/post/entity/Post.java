package com.back.boundedContext.post.entity;

import com.back.common.jpa.entity.BaseIdAndTime;
import com.back.boundedContext.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseIdAndTime {

    private String title;
    @ManyToOne (fetch = LAZY)
    private Member author;
    private String content;


    public Post(String title, Member author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }

}
