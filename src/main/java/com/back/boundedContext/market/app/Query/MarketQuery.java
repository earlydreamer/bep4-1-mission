package com.back.boundedContext.market.app.Query;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketQuery {
    private final com.back.boundedContext.market.out.repository.MarketMemberRepository marketMemberRepository;
    private final com.back.boundedContext.market.out.repository.ProductRepository productRepository;

    public long countProducts() {
        return productRepository.count();
    }

    public java.util.Optional<com.back.boundedContext.market.domain.MarketMember> findMemberByUsername(String username) {
        return marketMemberRepository.findByUsername(username);
    }


}
