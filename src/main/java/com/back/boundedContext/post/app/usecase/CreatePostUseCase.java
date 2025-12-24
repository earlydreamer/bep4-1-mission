package com.back.boundedContext.post.app.usecase;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.repository.CommentRepository;
import com.back.boundedContext.post.out.repository.PostRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.post.dto.PostCreatedEventPayload;
import com.back.shared.post.event.PostCreatedEvent;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePostUseCase {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final EventPublisher eventPublisher;


    @Transactional
    public Post CreatePost(String title, Member author, String content){
        //새 포스트를 작성해 DB에 저장하는 로직은 여기서 발생한다. 생성자를 통해 직접 밀어넣는다.
        Post post = postRepository.save(new Post(title, author, content));

        //이벤트 발행. 이후의 동작은 이벤트가 담당한다.
        eventPublisher.publish(new PostCreatedEvent(new PostCreatedEventPayload(post)));
        return post;
    }


}
