package com.back.boundedContext.post.entity;

import com.back.common.jpa.entity.BaseIdAndTime;
import com.back.boundedContext.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Post extends BaseIdAndTime {

    private String title;
    @ManyToOne
    private Member author;
    private String content;


    public Post(String title, Member author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }

}
