package com.back.boundedContext.payout.app.facade;

import com.back.boundedContext.payout.app.query.PayoutQuery;
import com.back.boundedContext.payout.app.usecase.PayoutAddPayoutCandidateItemsUseCase;
import com.back.boundedContext.payout.app.usecase.PayoutCollectPayoutItemsMoreUseCase;
import com.back.boundedContext.payout.app.usecase.PayoutCreatePayoutUseCase;
import com.back.boundedContext.payout.app.usecase.PayoutSyncMemberUseCase;
import com.back.boundedContext.payout.domain.Payout;
import com.back.boundedContext.payout.domain.PayoutCandidateItem;
import com.back.global.rsData.RsData;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayoutFacade {
    private final PayoutSyncMemberUseCase payoutSyncMemberUseCase;
    private final PayoutCreatePayoutUseCase payoutCreatePayoutUseCase;
    private final PayoutAddPayoutCandidateItemsUseCase payoutAddPayoutCandidateItemsUseCase;
    private final PayoutCollectPayoutItemsMoreUseCase payoutCollectPayoutItemsMoreUseCase;
    private final PayoutQuery payoutQuery;

    @Transactional
    public void syncMember(MemberDto member) {
        payoutSyncMemberUseCase.syncMember(member);
    }

    @Transactional
    public Payout createPayout(PayoutMemberDto payee) {
        return payoutCreatePayoutUseCase.createPayout(payee);
    }

    @Transactional
    public void addPayoutCandidateItems(OrderDto order) {
        payoutAddPayoutCandidateItemsUseCase.addPayoutCandidateItems(order);
    }


    @Transactional
    public RsData<Integer> collectPayoutItemsMore(int limit) {
        return payoutCollectPayoutItemsMoreUseCase.collectPayoutItemsMore(limit);
    }

    @Transactional(readOnly = true)
    public List<PayoutCandidateItem> findPayoutCandidateItems() {
        return payoutQuery
                .findPayoutCandidateItems();
    }
}