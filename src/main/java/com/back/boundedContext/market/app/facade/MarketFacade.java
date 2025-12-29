package com.back.boundedContext.market.app.facade;

import com.back.boundedContext.market.app.usecase.MarketSyncMemberUseCase;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.shared.member.dto.MemberJoinedEventPayload;
import com.back.shared.post.dto.MemberUpdatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MarketFacade {
    private final MarketSyncMemberUseCase marketSyncMemberUseCase;

    @Transactional
    public MarketMember syncMember(MemberJoinedEventPayload member) {
        return marketSyncMemberUseCase.syncMember(member);
    }

    @Transactional
    public MarketMember syncMember(MemberUpdatedEventPayload member) {
        return marketSyncMemberUseCase.syncMember(member);
    }
}
