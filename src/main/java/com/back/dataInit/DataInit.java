package com.back.dataInit;

import com.back.entity.Member;
import com.back.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;


/**
 * 데이터 초기화를 위한 클래스
 */
@Configuration
@Slf4j
public class DataInit {
    private final DataInit self;
    private final MemberService memberService;

    public DataInit(@Lazy DataInit self, MemberService memberService) {
        this.self = self;
        this.memberService = memberService;
    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberService.count() > 0) return; //멤버가 있으면 초기화 안해도 된다
        // 이런식으로 검증하면 좀 위험할수도 있어 보이는데
        // transactional이니까 이 코드가 끝까지 실행되면 들어가고 실패하면 통쨰로 롤백됨
        // 다만 데이터가 이후에 변경되거나 삭제되었을 때 이 init 코드가 작동 안할 수 있다. 초기화를 다시 하려면 날려야 할것 같은데
        Member systemMember = memberService.join("system", "1234", "시스템");
        Member holdingMember = memberService.join("holding", "1234", "홀딩");
        Member adminMember = memberService.join("admin", "1234", "관리자");
        Member user1Member = memberService.join("user1", "1234", "유저1");
        Member user2Member = memberService.join("user2", "1234", "유저2");
        Member user3Member = memberService.join("user3", "1234", "유저3");


    }
}