package com.back.boundedContext.post.app.facade;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.out.repository.MemberRepository;
import com.back.boundedContext.post.app.usecase.CreatePostUseCase;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.repository.PostMemberRepository;
import com.back.boundedContext.post.out.repository.PostRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.global.rsData.RsData;
import com.back.shared.member.dto.MemberJoinedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Facade는 이 BoundedContext 안으로 들어오는 모든 요청에 대한 관문이다.
 */
@Service
@RequiredArgsConstructor
public class PostFacade {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final EventPublisher eventPublisher;
    private final CreatePostUseCase createPostUseCase;
    private final PostMemberRepository postMemberRepository;


    public RsData<Post> createPost(String title, Member author, String content){
        return createPostUseCase.CreatePost(title, author, content);
    }

    public Optional<Post> findByPostId(int i) {
        return postRepository.findById(i);
    }
    public long count() {
        return postRepository.count();
    }

    /**
     * 새로 생성되는 MemberMember와 PostMember의 내용을 연동한다.
     * 예제코드상에는 Facade에 직접 박혀 있어서 일단 구현했는데 UseCase로 빼는게 낫지 않나
     * @param member
     * @return
     */
    public PostMember syncMember(MemberJoinedEventPayload member) {
        PostMember postMember = new PostMember(member.getId(), member.getCreatedAt(), member.getUpdatedAt(),
                member.getNickname(),"",member.getNickname());
        //민감정보인 Password는 미러링에 넘기지 않는다.
        //필드 자체는 있어야 하는 정보이므로 필드 자체를 날리는 것이 아니라 공백을 넣는다
        //새로 생성되는 Score는 생성자에서 0으로 초기화된다.
        //초기화 책임을 sync 로직에서 생성자 쪽으로 이전
        return postMemberRepository.save(postMember);
    }



}
