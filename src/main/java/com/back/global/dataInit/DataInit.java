package com.back.global.dataInit;

import com.back.boundedContext.member.app.facade.MemberFacade;
import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.app.facade.CommentFacade;
import com.back.boundedContext.post.app.facade.PostFacade;
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
    private final MemberFacade memberFacade;
    private final PostFacade postFacade;
    private final CommentFacade commentFacade;

    public DataInit(@Lazy DataInit self, MemberFacade memberFacade, PostFacade postService, CommentFacade commentFacade) {
        this.self = self;
        this.memberFacade = memberFacade;
        this.postFacade = postService;
        this.commentFacade = commentFacade;

    }

    @Bean
    public ApplicationRunner baseInitDataRunner() {
        return args -> {
            self.makeBaseMembers();
            self.makeBasePosts();
            self.makeComment();
        };
    }

    @Transactional
    public void makeBaseMembers() {
        if (memberFacade.count() > 0) return; //멤버가 있으면 초기화 안해도 된다
        // 이런식으로 검증하면 좀 위험할수도 있어 보이는데
        // transactional이니까 이 코드가 끝까지 실행되면 들어가고 실패하면 통쨰로 롤백됨
        // 다만 데이터가 이후에 변경되거나 삭제되었을 때 이 init 코드가 작동 안할 수 있다. 초기화를 다시 하려면 날려야 할것 같은데
        // 인위적으로 초기화를 런타임에 돌릴 게 아니라면 상관없긴 하겠다. 초기값이 들어가고 나면 이후의 변경은 이 메소드의 책임을 벗어난다.
        Member systemMember = memberFacade.join("system", "1234", "시스템").getData();
        Member holdingMember = memberFacade.join("holding", "1234", "홀딩").getData();
        Member adminMember = memberFacade.join("admin", "1234", "관리자").getData();
        Member user1Member = memberFacade.join("user1", "1234", "유저1").getData();
        Member user2Member = memberFacade.join("user2", "1234", "유저2").getData();
        Member user3Member = memberFacade.join("user3", "1234", "유저3").getData();

    }

    public void makeBasePosts() {
        //user1 회원(4번 회원)이 글 3개 작성
        //user2 회원(5번 회원)이 글 2개 작성
        //user3 회원(6번 회원)이 글 1개 작성

        if(postFacade.count()>0) return;

        Optional<Member> user1Member = memberFacade.findByUsername("user1");
        Optional<Member> user2Member = memberFacade.findByUsername("user2");
        Optional<Member> user3Member = memberFacade.findByUsername("user3");

        postFacade.createPost("제목1", user1Member.get(), "내용1").getData();
        postFacade.createPost("제목2", user1Member.get(), "내용2").getData();
        postFacade.createPost("제목3", user1Member.get(), "내용3").getData();
        postFacade.createPost("제목4", user2Member.get(), "내용4").getData();
        postFacade.createPost("제목5", user2Member.get(), "내용5").getData();
        postFacade.createPost("제목6", user3Member.get(), "내용6").getData();

    }

    public void makeComment() {
        //user1 회원이 1번글에 댓글(내용=댓글1) 작성
        //user2 회원이 1번글에 댓글(내용=댓글2) 작성
        //user3 회원이 1번글에 댓글(내용=댓글3) 작성
        //user2 회원이 2번글에 댓글(내용=댓글4) 작성
        //user2 회원이 2번글에 댓글(내용=댓글5) 작성
        //user3 회원이 3번글에 댓글(내용=댓글6) 작성
        //user1 회원이 3번글에 댓글(내용=댓글7) 작성
        //user1 회원이 4번글에 댓글(내용=댓글8) 작성

        if(commentFacade.count()>0) return;

        Optional<Member> user1Member = memberFacade.findByUsername("user1");
        Optional<Member> user2Member = memberFacade.findByUsername("user2");
        Optional<Member> user3Member = memberFacade.findByUsername("user3");

        Optional<Post> post1 = postFacade.findByPostId(1);
        Optional<Post> post2 = postFacade.findByPostId(2);
        Optional<Post> post3 = postFacade.findByPostId(3);
        Optional<Post> post4 = postFacade.findByPostId(4);

        commentFacade.createComment(post1.get(), user1Member.get(), "댓글1").getData();
        commentFacade.createComment(post1.get(), user2Member.get(), "댓글2").getData();
        commentFacade.createComment(post1.get(), user3Member.get(), "댓글3").getData();

        commentFacade.createComment(post2.get(), user2Member.get(), "댓글4").getData();
        commentFacade.createComment(post2.get(), user2Member.get(), "댓글5").getData();

        commentFacade.createComment(post3.get(), user3Member.get(), "댓글6").getData();
        commentFacade.createComment(post3.get(), user3Member.get(), "댓글7").getData();
//      commentService.createComment(post3.get(), user1Member.get(), "댓글7");
//      왜 값이 다른가 했더니 예제와 값이 달랐음 (예제 쪽에 오타인 듯)

        commentFacade.createComment(post4.get(), user1Member.get(), "댓글8");



    }
}