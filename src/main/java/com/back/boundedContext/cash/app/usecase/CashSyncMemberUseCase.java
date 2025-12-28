package com.back.boundedContext.cash.app.usecase;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.repository.CashMemberRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.cash.dto.CashMemberCreatedEventPayload;
import com.back.shared.member.dto.MemberJoinedEventPayload;
import com.back.shared.cash.event.CashMemberCreatedEvent;
import com.back.shared.post.dto.MemberUpdatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {

    private final CashMemberRepository cashMemberRepository;
    private final EventPublisher eventPublisher;

    /**
     * 새로 가입한 Member를 CashMember로 동기화한다.
     * MemberJoinedEvent는 항상 새로운 멤버이므로, CashMember 생성 후 CashMemberCreatedEvent를 발행한다.
     * 업데이트와 생성의 싱크가 분리된 이벤트이므로 예제처럼 isNew 체크를 할 필요 없이 생성 쪽에만 CashMemberCreatedEvent를 넣는다.
     * @param member MemberJoinedEventPayload
     * @return 생성된 CashMember
     */
    @Transactional
    public CashMember syncMember(MemberJoinedEventPayload member) {
        //민감정보인 Password는 미러링에 넘기지 않는다.
        //필드 자체는 있어야 하는 정보이므로 필드 자체를 날리는 것이 아니라 공백을 넣는다
        //새로 생성되는 activityScore는 0으로 초기화된다.
        CashMember cashMember = new CashMember(
                member.getId(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getUsername(),
                "",
                member.getNickname(),
                0
        );
        CashMember savedMember = cashMemberRepository.save(cashMember);

        // CashMember 생성 완료 후 CashMemberCreatedEvent 발행
        eventPublisher.publish(
                new CashMemberCreatedEvent(
                        new CashMemberCreatedEventPayload(savedMember)
                )
        );

        return savedMember;
    }


    /**
     * Member의 activityScore가 업데이트될 때 CashMember의 activityScore를 동기화한다.
     * 기존 엔티티를 찾아 수정하는 것이 아니라, 새 객체를 생성하여 save()를 호출한다.
     * JPA가 ID가 존재함을 확인하고 UPDATE(merge)를 수행한다.
     * @param member 업데이트된 Member 정보를 담고 있는 Payload
     * @return 업데이트된 CashMember
     */
    @Transactional
    public CashMember syncMember(MemberUpdatedEventPayload member) {
        CashMember cashMember =  new CashMember(
                member.getId(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getUsername(),
                "",
                member.getNickname(),
                member.getActivityScore()
        );
        return cashMemberRepository.save(cashMember);
    }


}
