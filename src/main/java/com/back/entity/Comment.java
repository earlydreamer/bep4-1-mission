package com.back.entity;

import com.back.jpa.entity.BaseIdAndTime;
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
