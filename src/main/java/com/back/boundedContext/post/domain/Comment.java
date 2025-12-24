package com.back.boundedContext.post.domain;

import com.back.boundedContext.member.domain.Member;
import com.back.global.jpa.entity.BaseIdAndTime;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name="POST_COMMENT")
public class Comment extends BaseIdAndTime {
    @ManyToOne (fetch = LAZY)
    @JoinColumn(name = "post_id")
    Post post;
    @ManyToOne (fetch = LAZY)
    @JoinColumn(name = "author_id")
    private Member author;
    private String content;

    public Comment (Post post, Member author, String content ){
        this.post = post;
        this.author = author;
        this.content = content;
    }

}
