package com.back.boundedContext.market.app.facade;

import com.back.boundedContext.market.app.Query.MarketQuery;
import com.back.boundedContext.market.app.usecase.MarketCreateCartUseCase;
import com.back.boundedContext.market.app.usecase.MarketCreateOrderUseCase;
import com.back.boundedContext.market.app.usecase.MarketCreateProductUseCase;
import com.back.boundedContext.market.app.usecase.MarketSyncMemberUseCase;
import com.back.boundedContext.market.domain.Cart;
import com.back.boundedContext.market.domain.MarketMember;
import com.back.boundedContext.market.domain.Order;
import com.back.boundedContext.market.domain.Product;
import com.back.global.rsData.RsData;
import com.back.shared.market.dto.MarketMemberCreatedEventPayload;
import com.back.shared.member.dto.MemberJoinedEventPayload;
import com.back.shared.post.dto.MemberUpdatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketFacade {

    private final MarketSyncMemberUseCase marketSyncMemberUseCase;
    private final MarketQuery marketQuery;

    private final MarketCreateProductUseCase marketCreateProductUseCase;
    private final MarketCreateCartUseCase marketCreateCartUseCase;
    private final MarketCreateOrderUseCase marketCreateOrderUseCase;

    @Transactional
    public MarketMember syncMember(MemberJoinedEventPayload member) {
        return marketSyncMemberUseCase.syncMember(member);
    }

    @Transactional
    public MarketMember syncMember(MemberUpdatedEventPayload member) {
        return marketSyncMemberUseCase.syncMember(member);
    }


    @Transactional(readOnly = true)
    public long productsCount() {
        return marketQuery.countProducts();
    }

    @Transactional
    public Product createProduct(
            MarketMember seller,
            String sourceTypeCode,
            Long sourceId,
            String name,
            String description,
            int price,
            int salePrice
    ) {

        return marketCreateProductUseCase.createProduct(
                seller,
                sourceTypeCode,
                sourceId,
                name,
                description,
                price,
                salePrice
        );
    }

    @Transactional(readOnly = true)
    public Optional<MarketMember> findMemberByUsername(String username) {
        return marketQuery.findMemberByUsername(username);
    }


    @Transactional
    public RsData<Cart> createCart(MarketMemberCreatedEventPayload buyer) {
        return marketCreateCartUseCase.createCart(buyer);
    }

    @Transactional(readOnly = true)
    public Optional<Cart> findCartByBuyer(MarketMember buyer) {
        return marketQuery.findCartByBuyer(buyer);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findProductById(Long id) {
        return marketQuery.findProductById(id);
    }

    @Transactional(readOnly = true)
    public long ordersCount() {
        return marketQuery.countOrders();
    }

    @Transactional
    public RsData<Order> createOrder(Cart cart) {
        return marketCreateOrderUseCase.createOrder(cart);
    }
}
