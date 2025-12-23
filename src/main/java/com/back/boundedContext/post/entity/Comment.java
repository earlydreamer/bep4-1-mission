package com.back.boundedContext.post.entity;

import com.back.common.jpa.entity.BaseIdAndTime;
import com.back.boundedContext.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Comment extends BaseIdAndTime {
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Member author;
    private String content;

    public Comment (Post post, Member author, String content ){
        this.post = post;
        this.author = author;
        this.content = content;
    }

}
