package com.back.boundedContext.payout.out.repository;

import com.back.boundedContext.payout.domain.Payout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutRepository extends JpaRepository<Payout, Long> {
}
