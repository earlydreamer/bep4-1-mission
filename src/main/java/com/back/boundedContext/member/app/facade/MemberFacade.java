package com.back.boundedContext.member.app.facade;


import com.back.boundedContext.member.app.usecase.MemberJoinUseCase;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.domain.MemberPolicy;
import com.back.boundedContext.member.out.repository.MemberRepository;
import com.back.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberFacade {
   private final MemberJoinUseCase memberJoinUseCase;
   private final MemberRepository memberRepository;
   private final MemberPolicy memberPolicy;

   @Transactional
   public long count() {
        return memberRepository.count();
    }

   @Transactional
    public RsData<Member> join(String username, String password, String nickname){
        return memberJoinUseCase.join(username, password, nickname);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<Member> findById(int id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public String getRandomSecureTip() {
        return "비밀번호의 유효기간은 %d일 입니다."
                .formatted(memberPolicy.getNeedToChangePasswordDays());
    }

}
