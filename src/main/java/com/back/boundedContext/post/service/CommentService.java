package com.back.boundedContext.post.service;

import com.back.boundedContext.post.entity.Comment;
import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.common.enums.ScoreEnum;
import com.back.boundedContext.post.repository.CommentRepository;
import com.back.boundedContext.member.repository.MemberRepository;
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
