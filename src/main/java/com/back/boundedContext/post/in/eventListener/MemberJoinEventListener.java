package com.back.boundedContext.post.in.eventListener;

import com.back.boundedContext.post.app.facade.PostFacade;
import com.back.shared.member.event.MemberJoinedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

@Component
@RequiredArgsConstructor
public class MemberJoinEventListener {
    private final PostFacade postFacade;

    /**
     * MemberJoinEvent가 발생했을 때 Post측에서 동작하는 내용에 대해 정의하므로, Post.in에 위치한다.
     * MemberJoinEvent가 다른 도메인에 존재할 수 있다. 패키지명으로 구분되므로 클래스명이 같아도 상관은 없다.
     * 신규 추가된 Member를 받아서 ReplicaMember에 복제한다.
     * 복제할 ReplicaMember 엔티티 : PostMember
     * event에 담긴 MemberJoinedEventPayload의 값을 기반으로 PostMember 생성한다.
     *
     * @param event
     */
    @TransactionalEventListener(phase = AFTER_COMMIT)
    @Transactional(propagation = REQUIRES_NEW)
    public void handle(MemberJoinedEvent event) {
        postFacade.syncMember(event.getMember());
    }


}

