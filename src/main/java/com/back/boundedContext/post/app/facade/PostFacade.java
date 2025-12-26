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
import com.back.shared.post.dto.MemberUpdatedEventPayload;
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
        PostMember postMember = new PostMember(
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
        return postMemberRepository.save(postMember);
    }


    /**
     * Member의 activityScore가 업데이트될 때 PostMember의 activityScore를 동기화한다.
     * 기존 엔티티를 찾아 수정하는 것이 아니라, 새 객체를 생성하여 save()를 호출한다.
     * JPA가 ID가 존재함을 확인하고 UPDATE(merge)를 수행한다.
     * @param member 업데이트된 Member 정보를 담고 있는 Payload
     * @return 업데이트된 PostMember
     */
    public PostMember syncMember(MemberUpdatedEventPayload member) {
        PostMember postMember = new PostMember(
                member.getId(),
                member.getCreatedAt(),
                member.getUpdatedAt(),
                member.getUsername(),
                "",
                member.getNickname(),
                member.getActivityScore()
        );
        return postMemberRepository.save(postMember);
    }



}
