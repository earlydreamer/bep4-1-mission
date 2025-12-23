package com.back.boundedContext.member.in.eventListener;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.app.usecase.MemberService;
import com.back.global.enums.ScoreEnum;
import com.back.shared.post.event.CommentCreatedEvent;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class MemberEventListener {
    private final MemberService memberService;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCreatedEvent event) {
        Member member = memberService.findById(event.getPost().getAuthorId()).get();
        member.increasePoint(ScoreEnum.POST_CREATE.getScore());

    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(CommentCreatedEvent event) {
        Member member = memberService.findById(event.getCommentCreatedEventPayload().getAuthorId()).get();
        member.increasePoint(ScoreEnum.COMMENT_CREATE.getScore());

    }
}