package com.back.boundedContext.post.app.usecase;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Comment;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.repository.CommentRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.post.dto.CommentCreatedEventPayload;
import com.back.shared.post.event.CommentCreatedEvent;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final EventPublisher eventPublisher;

    public CommentService(CommentRepository commentRepository, EventPublisher eventPublisher) {
        this.commentRepository = commentRepository;
        this.eventPublisher=eventPublisher;
    }

    @Transactional
    public Comment createComment(Post post, Member author, String content){
        //새 코멘트를 작성해 DB에 저장하는 로직은 여기서 발생한다. 생성자를 통해 직접 밀어넣는다.
        Comment comment = new Comment(post, author, content);
        commentRepository.save(comment); // 리팩토링 필요함

        //이벤트 발행. 이후의 동작은 이벤트가 담당한다.
        eventPublisher.publish(new CommentCreatedEvent(new CommentCreatedEventPayload(comment)));
        return comment;
    }

    public long count() {
        return commentRepository.count();
    }

}
