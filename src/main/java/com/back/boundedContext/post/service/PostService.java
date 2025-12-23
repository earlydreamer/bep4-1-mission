package com.back.boundedContext.post.service;

import com.back.boundedContext.member.entity.Member;
import com.back.boundedContext.post.entity.Post;
import com.back.common.enums.ScoreEnum;
import com.back.boundedContext.member.repository.MemberRepository;
import com.back.boundedContext.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public Post CreatePost(String title, Member author, String content){
        Post post = new Post(title, author, content);
        author.increasePoint(ScoreEnum.POST_CREATE.getScore());
        memberRepository.save(author);
        postRepository.save(post);
        return post;

    }

    public Optional<Post> findByPostId(int i) {
        return postRepository.findById(i);
    }

    public long count() {
        return postRepository.count();
    }

}
