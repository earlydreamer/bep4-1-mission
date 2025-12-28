package com.back.boundedContext.cash.in.eventListener;

import com.back.boundedContext.cash.app.facade.CashFacade;
import com.back.shared.member.event.MemberJoinedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component("cashMemberJoinEventListener")//eventListener가 같은 이름일 경우 Bean 충돌이 발생할 수 있다. 명시적으로 이름 넣어주기
@RequiredArgsConstructor
public class MemberJoinEventListener {
    private final CashFacade cashFacade;

    /**
     * MemberJoinEvent가 발생했을 때 Cash측에서 동작하는 내용에 대해 정의하므로, Cash.in에 위치한다.
     * MemberJoinEvent가 다른 도메인에 존재할 수 있다. 패키지명으로 구분되므로 클래스명이 같아도 상관은 없다.
     * 신규 추가된 Member를 받아서 ReplicaMember에 복제한다.
     * 복제할 ReplicaMember 엔티티 : CashMember
     * event에 담긴 MemberJoinedEventPayload의 값을 기반으로 CashMember 생성한다.
     * CashMember 생성 시 CashMemberCreatedEvent가 발행되어 Wallet이 자동으로 생성된다.
     * @param event
     */
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        // CashMember 생성 시 내부에서 CashMemberCreatedEvent 발행
        // 해당 이벤트를 통해 Wallet이 생성됨
        cashFacade.syncMember(event.getMember());
    }


}

