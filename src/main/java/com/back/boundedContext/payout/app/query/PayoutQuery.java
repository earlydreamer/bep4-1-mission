package com.back.boundedContext.payout.app.query;

import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.repository.PayoutMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayoutQuery {

    private final PayoutMemberRepository payoutMemberRepository;

    public Optional<PayoutMember> findHolingMember() {
        return payoutMemberRepository.findByUsername("holding");
    }

    public Optional<PayoutMember> findMemberById(Long id) {
        return payoutMemberRepository.findById(id);
    }
}

