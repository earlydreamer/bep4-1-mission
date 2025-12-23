package com.back.dataInit;

import com.back.entity.Member;
import com.back.service.MemberService;
import com.back.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * 데이터 초기화를 위한 클래스
 */
@Configuration
@Slf4j

public class DataInit {
    private final DataInit self;
    private final MemberService memberService;
    private final PostService postService;

    public DataInit(@Lazy DataInit self, MemberService memberService, PostService postService) {
        this.self = self;
        this.memberService = memberService;
        this.postService = postService;

    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
            self.makeBasePosts();
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

    public void makeBasePosts(){
        //user1 회원(4번 회원)이 글 3개 작성
        //user2 회원(5번 회원)이 글 2개 작성
        //user3 회원(6번 회원)이 글 1개 작성
        Optional<Member> user1Member = memberService.findByUsername("user1");
        Optional<Member> user2Member = memberService.findByUsername("user2");
        Optional<Member> user3Member = memberService.findByUsername("user3");
        postService.CreatePost("제목1", user1Member.get(), "내용1");
        postService.CreatePost("제목2", user1Member.get(), "내용2");
        postService.CreatePost("제목3", user1Member.get(), "내용3");
        postService.CreatePost("제목4", user2Member.get(), "내용4");
        postService.CreatePost("제목5", user2Member.get(), "내용5");
        postService.CreatePost("제목6", user3Member.get(), "내용6");

    }

}