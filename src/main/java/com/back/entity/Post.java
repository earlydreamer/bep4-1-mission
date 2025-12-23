package com.back.entity;

import com.back.jpa.entity.BaseIdAndTime;
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
