package com.back.boundedContext.market.app.usecase;

import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.out.repository.MarketMemberRepository;
import com.back.shared.member.dto.MemberJoinedEventPayload;
import com.back.shared.post.dto.MemberUpdatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketSyncMemberUseCase {

    private final MarketMemberRepository marketMemberRepository;

    /**
     * 새로 가입한 Member를 MarketMember로 동기화한다.
     * @param member MemberJoinedEventPayload
     * @return 생성된 MarketMember
     */
    @Transactional
    public MarketMember syncMember(MemberJoinedEventPayload member) {
        MarketMember marketMember = new MarketMember(
                member.getId(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getUsername(),
                "",
                member.getNickname(),
                0
        );
        return marketMemberRepository.save(marketMember);
    }

    /**
     * Member의 activityScore가 업데이트될 때 MarketMember를 동기화한다.
     * @param member 업데이트된 Member 정보
     * @return 업데이트된 MarketMember
     */
    @Transactional
    public MarketMember syncMember(MemberUpdatedEventPayload member) {
        MarketMember marketMember = new MarketMember(
                member.getId(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getUsername(),
                "",
                member.getNickname(),
                member.getActivityScore()
        );
        return marketMemberRepository.save(marketMember);
    }
}

