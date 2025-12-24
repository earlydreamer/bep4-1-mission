package com.back.boundedContext.post.domain;

import com.back.boundedContext.member.domain.Member;
import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name="POST_POST")
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
