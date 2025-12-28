package com.back.boundedContext.cash.app.facade;


import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.domain.Wallet;
import com.back.boundedContext.cash.out.repository.CashMemberRepository;
import com.back.boundedContext.cash.out.repository.WalletRepository;
import com.back.shared.member.dto.MemberJoinedEventPayload;
import com.back.shared.post.dto.MemberUpdatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashFacade {
    private final CashMemberRepository cashMemberRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public Wallet createWallet(CashMember holder) {
        Wallet wallet = new Wallet(holder);

        return walletRepository.save(wallet);
    }

    @Transactional
    public long count() {
        return cashMemberRepository.count();
    }

    /**
     * CashMember 조회 - Cash 컨텍스트 내부에서 사용
     */
    public Optional<CashMember> findCashMemberById(Long id) {
        return cashMemberRepository.findById(id);
    }

    /**
     * CashMember 조회 - username으로 조회
     */
    @Transactional(readOnly = true)
    public Optional<CashMember> findCashMemberByUsername(String username) {
        return cashMemberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Wallet> findWalletByHolder(CashMember holder) {
        return walletRepository.findByHolder(holder);
    }

    /**
     * 새로 생성되는 MemberMember와 CashMember의 내용을 연동한다.
     * @param member
     * @return
     */
    @Transactional
    public CashMember syncMember(MemberJoinedEventPayload member) {
        CashMember cashMember = new CashMember(
                member.getId(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getUsername(),
                "",
                member.getNickname(),
                0
        );
        //민감정보인 Password는 미러링에 넘기지 않는다.
        //필드 자체는 있어야 하는 정보이므로 필드 자체를 날리는 것이 아니라 공백을 넣는다
        //새로 생성되는 activityScore는 0으로 초기화된다.
        return cashMemberRepository.save(cashMember);
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
