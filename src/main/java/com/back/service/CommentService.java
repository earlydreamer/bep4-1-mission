package com.back.service;

import com.back.entity.Comment;
import com.back.entity.Member;
import com.back.entity.Post;
import com.back.entity.enums.ScoreEnum;
import com.back.repository.CommentRepository;
import com.back.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    public CommentService(CommentRepository commentRepository, MemberRepository memberRepository) {
        this.commentRepository = commentRepository;
        this.memberRepository=memberRepository;
    }

    @Transactional
    public Comment createComment(Post post, Member author, String content){
        //코멘트 생성
        Comment comment = new Comment(post, author, content);
        author.increasePoint(ScoreEnum.COMMENT_CREATE.getScore());
        memberRepository.save(author);
        commentRepository.save(comment);
        return comment;
    }

    public long count() {
        return commentRepository.count();
    }

}
