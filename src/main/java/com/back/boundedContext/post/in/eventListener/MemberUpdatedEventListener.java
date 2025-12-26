package com.back.boundedContext.post.in.eventListener;

import com.back.boundedContext.post.app.facade.PostFacade;
import com.back.shared.post.event.MemberUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class MemberUpdatedEventListener {
    private final PostFacade postFacade;

    /**
     * MemberUpdatedEvent가 발생했을 때 Post측에서 동작하는 내용에 대해 정의한다.
     * Member의 activityScore가 변경되었을 때 PostMember의 activityScore를 동기화한다.
     * event에 담긴 MemberUpdatedEventPayload의 값을 기반으로 PostMember를 업데이트한다.
     *
     * @param event
     */
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberUpdatedEvent event) {
        postFacade.syncMember(event.getMember());
    }
}
