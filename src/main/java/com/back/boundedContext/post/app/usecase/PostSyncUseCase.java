package com.back.boundedContext.post.app.usecase;

import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.repository.PostMemberRepository;
import com.back.shared.member.dto.MemberJoinedEventPayload;
import com.back.shared.post.dto.MemberUpdatedEventPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostSyncUseCase {

    private final PostMemberRepository postMemberRepository;

    /**
     * 새로 생성되는 MemberMember와 PostMember의 내용을 연동한다.
     * @param member
     * @return
     */
    @Transactional
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
    @Transactional
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
