package com.back.boundedContext.member.in.eventListener;

import com.back.boundedContext.member.app.facade.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.global.enums.ScoreEnum;
import com.back.shared.post.dto.MemberUpdatedEventPayload;
import com.back.shared.post.event.CommentCreatedEvent;
import com.back.shared.post.event.MemberUpdatedEvent;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.back.global.config.GlobalConfig.eventPublisher;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class ScoreEventListener {
    private final MemberFacade memberFacade;

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCreatedEvent event) {
        Member member = memberFacade.findById(event.getPost().getAuthorId()).get();
        member.increasePoint(ScoreEnum.POST_CREATE.getScore());
        memberFacade.save(member);
        eventPublisher.publish(new MemberUpdatedEvent(new MemberUpdatedEventPayload(member)));
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(CommentCreatedEvent event) {
        Member member = memberFacade.findById(event.getCommentCreatedEventPayload().getAuthorId()).get();
        member.increasePoint(ScoreEnum.COMMENT_CREATE.getScore());
        memberFacade.save(member);
        eventPublisher.publish(new MemberUpdatedEvent(new MemberUpdatedEventPayload(member)));
    }



}