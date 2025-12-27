package com.back.boundedContext.cash.in.eventListener;

import com.back.boundedContext.cash.app.facade.CashFacade;
import com.back.shared.post.event.MemberUpdatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component("cashMemberUpdatedEventListener")
@RequiredArgsConstructor
public class MemberUpdatedEventListener {
    private final CashFacade cashFacade;

    /**
     * MemberUpdatedEvent가 발생했을 때 Cash측에서 동작하는 내용에 대해 정의한다.
     * Member의 activityScore가 변경되었을 때 CashMember의 activityScore를 동기화한다.
     * event에 담긴 MemberUpdatedEventPayload의 값을 기반으로 CashMember를 업데이트한다.
     *
     * @param event
     */
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberUpdatedEvent event) {
        cashFacade.syncMember(event.getMember());
    }
}
