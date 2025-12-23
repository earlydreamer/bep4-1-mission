package com.back.service;

import com.back.entity.Comment;
import com.back.entity.Member;
import com.back.entity.Post;
import com.back.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Post post, Member author, String content){
        Comment comment = new Comment(post, author, content);
        commentRepository.save(comment);
        return comment;
    }

    public long count() {
        return commentRepository.count();
    }

}
