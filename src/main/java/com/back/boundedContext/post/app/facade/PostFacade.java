package com.back.boundedContext.post.app.facade;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.out.repository.MemberRepository;
import com.back.boundedContext.post.app.usecase.CreatePostUseCase;
import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.out.repository.PostRepository;
import com.back.global.eventPublisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostFacade {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final EventPublisher eventPublisher;
    private final CreatePostUseCase createPostUseCase;


    public Post createPost(String title, Member author, String content){
        return createPostUseCase.CreatePost(title, author, content);
    }

    public Optional<Post> findByPostId(int i) {
        return postRepository.findById(i);
    }
    public long count() {
        return postRepository.count();
    }

}
