package com.back.boundedContext.payout.out.repository;

import com.back.boundedContext.payout.domain.Payout;
import com.back.boundedContext.payout.domain.PayoutMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayoutRepository extends JpaRepository<Payout, Long> {
    Optional<Payout> findByPayeeAndPayoutDateIsNull(PayoutMember payee);
}
