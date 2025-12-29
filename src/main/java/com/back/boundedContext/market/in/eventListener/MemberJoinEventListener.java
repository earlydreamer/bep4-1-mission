package com.back.boundedContext.market.in.eventListener;

import com.back.boundedContext.market.app.facade.MarketFacade;
import com.back.shared.member.event.MemberJoinedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component("marketMemberJoinEventListener")
@RequiredArgsConstructor
public class MemberJoinEventListener {

    private final MarketFacade marketFacade;

    /**
     * MemberJoinedEvent가 발생했을 때 MarketMember를 생성한다.
     * @param event MemberJoinedEvent
     */
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        marketFacade.syncMember(event.getMember());
    }
}
