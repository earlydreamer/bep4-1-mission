package com.back.boundedContext.member.in.eventListener;

import com.back.boundedContext.member.app.facade.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.global.enums.ScoreEnum;
import com.back.shared.post.event.CommentCreatedEvent;
import com.back.shared.post.event.PostCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

/**
 * Post 컨텍스트의 활동(게시글, 댓글)에 반응하여 Member에게 보상을 지급하는 리스너
 * Application Layer의 역할: 컨텍스트 간 조율
 * 이벤트 발행은 Member 도메인이 담당
 */
@Component
@RequiredArgsConstructor
public class ScoreEventListener {
    private final MemberFacade memberFacade;

    /**
     * 게시글 작성 시 작성자에게 보상 지급
     */
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(PostCreatedEvent event) {
        Member member = memberFacade.findById(event.getPost().getAuthorId()).get();
        member.increasePoint(ScoreEnum.POST_CREATE.getScore());
        memberFacade.save(member);
        // 이벤트 발행은 Member 도메인에서 자동으로 처리됨
    }

    /**
     * 댓글 작성 시 작성자에게 보상 지급
     */
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(CommentCreatedEvent event) {
        Member member = memberFacade.findById(event.getCommentCreatedEventPayload().getAuthorId()).get();
        member.increasePoint(ScoreEnum.COMMENT_CREATE.getScore());
        memberFacade.save(member);
        // 이벤트 발행은 Member 도메인에서 자동으로 처리됨
    }



}